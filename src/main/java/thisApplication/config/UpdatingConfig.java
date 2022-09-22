package thisApplication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.dto.door.DoorDto;
import thisApplication.model.entity.camera.CameraEntity;
import thisApplication.model.entity.door.DoorEntity;
import thisApplication.repository.CameraRepository;
import thisApplication.repository.DoorRepository;
import thisApplication.service.RetrofitService;

import java.util.List;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class UpdatingConfig {
    private final CameraRepository cameraRepository;
    private final DoorRepository doorRepository;
    private final RetrofitService retrofitService;
    @Bean
    void create() {
        /*Обновление данных про камеры*/
        //assert response1.body() != null; найти альтернативу

        List<CameraDto> cameras = retrofitService.response("CameraDto");
        cameras.forEach(c -> cameraRepository.save(new CameraEntity().builder()
                .name(c.getName())
                .id(c.getId())
                .room(c.getRoom())
                .snapshot(c.getSnapshot())
                .build()
        ));

        /*Обновление данных про двери*/
        // Calling '/api/rubetek/doors'
        //assert response2.body() != null;

        List<DoorDto> doors = retrofitService.response("DoorDto");
        doors.forEach(c -> doorRepository.save(new DoorEntity().builder()
                .name(c.getName())
                .snapshot(c.getSnapshot())
                .id(c.getId())
                .room(c.getRoom())
                .build()
        ));
    }

    @Scheduled(fixedRateString = "${milliseconds}")
    void update() {
        /*Обновление данных про камеры*/
        // Calling '/api/rubetek/cameras'
        //assert response1.body() != null;
        List<CameraDto> cameras = retrofitService.response("CameraDto");
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

        /*Обновление данных про двери*/
        // Calling '/api/rubetek/doors'
        //assert response2.body() != null;
        List<DoorDto> doors = retrofitService.response("DoorDto");
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
    }
}