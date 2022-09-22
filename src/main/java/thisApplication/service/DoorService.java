package thisApplication.service;

import org.springframework.stereotype.Service;
import thisApplication.model.dto.door.DoorDto;
import thisApplication.model.entity.door.DoorEntity;

import java.util.List;

@Service
public interface DoorService {
    List<DoorEntity> getAll();

    List<DoorDto> getDoorsInRoom(String Room);

    List<DoorDto> getFavoritesDoors();

    void setDoorFavorite(String door);

    void saveEntity(DoorEntity doorEntity);
}