package thisApplication.service;

import org.springframework.stereotype.Service;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.entity.camera.CameraEntity;

import java.util.List;

@Service
public interface CameraService {
    List<CameraEntity> getAll();

    List <String> getRooms();

    List<CameraDto> getCamerasInRoom(String Room);

    List<CameraDto> getFavoritesCameras();

    void setCameraFavorite(String name);
    
    void setCameraRec(String name);

    void saveEntity(CameraEntity cameraEntity);
}
