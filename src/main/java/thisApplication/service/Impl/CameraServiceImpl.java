package thisApplication.service.Impl;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.dto.room.RoomDto;
import thisApplication.model.entity.camera.CameraEntity;
import thisApplication.repository.CameraRepository;
import thisApplication.repository.RoomRepository;
import thisApplication.service.CameraService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CameraServiceImpl implements CameraService {
    private final CameraRepository cameraRepository;
    private final RoomRepository roomRepository;
    @Override
    @Transactional(readOnly = true)
    public List<CameraEntity> getAll() {
        return cameraRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public @Nullable List<String> getRooms() {
        return roomRepository.findAll().stream()
                .map(new RoomDto()::mapEntityToDto)
                .map(RoomDto::getName)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraDto> getCamerasInRoom(String room) {
        return cameraRepository.findAllByRoom(room).stream()
                .map(new CameraDto()::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraDto> getFavoritesCameras() {
        return cameraRepository.findAllByFavoritesIsTrue().stream()
                .map(new CameraDto()::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setCameraFavorite(String name) {
        CameraEntity cameraEntity = cameraRepository.findCameraEntityByName(name);
        cameraEntity.setFavorites(true);
        cameraRepository.save(cameraEntity);
    }

    @Override
    @Transactional
    public void setCameraRec(String name) {
        CameraEntity cameraEntity = cameraRepository.findCameraEntityByName(name);
        cameraEntity.setRec(!cameraEntity.isRec());
        cameraRepository.save(cameraEntity);
    }

    @Override
    @Transactional
    public void saveEntity(CameraEntity cameraEntity) {
        cameraRepository.save(cameraEntity);
    }
}
