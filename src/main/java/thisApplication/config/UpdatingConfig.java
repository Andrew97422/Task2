package thisApplication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import thisApplication.config.enums.DtoType;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.dto.door.DoorDto;
import thisApplication.model.dto.room.RoomDto;
import thisApplication.model.entity.camera.CameraEntity;
import thisApplication.model.entity.door.DoorEntity;
import thisApplication.model.entity.room.RoomEntity;
import thisApplication.repository.CameraRepository;
import thisApplication.repository.DoorRepository;
import thisApplication.repository.RoomRepository;
import thisApplication.service.RetrofitService;

import java.util.List;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class UpdatingConfig {
    private final CameraRepository cameraRepository;
    private final DoorRepository doorRepository;
    private final RoomRepository roomRepository;
    private final RetrofitService retrofitService;
    @Bean
    void create() {
        /*Создание данных про камеры*/
        List<CameraDto> cameras = retrofitService.response(DtoType.CAMERA_DTO);
        if (cameras != null) {
            cameras.forEach(c -> cameraRepository.save(CameraEntity.builder()
                    .name(c.getName())
                    .id(c.getId())
                    .room(c.getRoom())
                    .snapshot(c.getSnapshot())
                    .build()
            ));
        } else throw new NullPointerException();

        /*Создание данных про двери*/
        List<DoorDto> doors = retrofitService.response(DtoType.DOOR_DTO);
        if (doors != null) {
            doors.forEach(c -> doorRepository.save(DoorEntity.builder()
                    .name(c.getName())
                    .snapshot(c.getSnapshot())
                    .id(c.getId())
                    .room(c.getRoom())
                    .build()
            ));
        } else throw new NullPointerException();

        /*Создание данных про комнаты*/
        List<String> roomsString = retrofitService.response(DtoType.ROOM_DTO);
        if (roomsString != null) {
            List<RoomDto> rooms = roomsString.stream().map(RoomDto::new).toList();
            if (!rooms.contains(new RoomDto().getName())) {
                rooms.forEach(c -> roomRepository.save(RoomEntity.builder()
                        .name(c.getName())
                        .build()
                ));
            } else throw new NullPointerException();
        }
    }

    @Scheduled(fixedRateString = "${milliseconds}")
    void update() {
        /*Обновление данных про камеры*/
        List<CameraDto> cameras = retrofitService.response(DtoType.CAMERA_DTO);
        if (cameras != null) {
            List<CameraEntity> cameraEntities = cameras.stream()
                    .map(c -> cameraRepository.findById(c.getId())
                            .map(entity -> { // Если есть в базе
                                entity.setRoom(c.getRoom());
                                entity.setName(c.getName());
                                entity.setSnapshot(c.getSnapshot());
                                return entity;
                            })
                            .orElseGet(() -> { // Если нет в базе
                                CameraEntity entity = c.mapDtoToEntity();
                                entity.setFavorites(c.isFavorites());
                                entity.setRec(c.isRec());
                                return entity;
                            })
                    ).toList();

            cameraRepository.saveAll(cameraEntities);
        } else throw new NullPointerException();

        /*Обновление данных про двери*/
        List<DoorDto> doors = retrofitService.response(DtoType.DOOR_DTO);
        if (doors != null) {
            List<DoorEntity> doorEntities = doors.stream()
                    .map(c -> doorRepository.findById(c.getId())
                            .map(entity -> { // Если есть в базе
                                entity.setRoom(c.getRoom());
                                entity.setName(c.getName());
                                entity.setSnapshot(c.getSnapshot());
                                return entity;
                            })
                            .orElseGet(() -> { // Если нет в базе
                                DoorEntity entity = c.mapDtoToEntity();
                                entity.setFavorites(c.isFavorites());
                                return entity;
                            })
                    ).toList();

            doorRepository.saveAll(doorEntities);
        } else throw new NullPointerException();

        /*Обновление данных про комнаты*/
        List<String> roomsString = retrofitService.response(DtoType.ROOM_DTO);
        if (roomsString != null) {
            List<RoomDto> rooms = roomsString.stream().map(RoomDto::new).toList();
            List<RoomEntity> roomEntities = rooms.stream()
                    .map(c -> roomRepository.findById(c.getName())
                            .map(entity -> {
                                entity.setName(c.getName());
                                return entity;
                            })
                            .orElseGet(c::mapDtoToEntity)
                    ).toList();

            roomRepository.saveAll(roomEntities);
        } else throw new NullPointerException();
    }
}