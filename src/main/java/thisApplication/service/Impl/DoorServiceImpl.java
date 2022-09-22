package thisApplication.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisApplication.model.dto.door.DoorDto;
import thisApplication.model.entity.door.DoorEntity;
import thisApplication.repository.DoorRepository;
import thisApplication.service.DoorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService {
    private final DoorRepository doorRepository;
    @Override
    @Transactional(readOnly = true)
    public List<DoorEntity> getAll() {
        return doorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoorDto> getDoorsInRoom(String room) {
        return doorRepository.findDoorEntitiesByRoom(room).stream()
                .map(new DoorDto()::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoorDto> getFavoritesDoors() {
        return doorRepository.findAllByFavoritesIsTrue().stream()
                .map(new DoorDto()::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setDoorFavorite(String name) {
        DoorEntity doorEntity = doorRepository.findDoorEntityByName(name);
        doorEntity.setFavorites(true);
        doorRepository.save(doorEntity);
    }

    @Override
    @Transactional
    public void saveEntity(DoorEntity doorEntity) {
        doorRepository.save(doorEntity);
    }
}
