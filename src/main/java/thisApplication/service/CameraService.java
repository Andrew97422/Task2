package thisApplication.service;

import org.springframework.stereotype.Service;
import thisApplication.model.dto.CameraDto;
import thisApplication.model.entity.CameraEntity;

import java.util.List;

@Service
public interface CameraService {
    List<CameraEntity> getAll();

    String[] getRooms();

    List<CameraDto> getCamerasInRoom(String Room);

    List<CameraDto> getFavoritesCameras();

    void setCameraFavorite(String name);
    
    void setCameraRec(String name);

    void saveEntity(CameraEntity cameraEntity);
}
