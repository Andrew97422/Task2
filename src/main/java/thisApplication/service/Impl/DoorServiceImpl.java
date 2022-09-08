package thisApplication.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisApplication.model.dto.DoorDto;
import thisApplication.model.entity.DoorEntity;
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
    public List<DoorDto> getDoorsInRoom(String Room) {
        return doorRepository.findAll().stream()
                .map(doorEntity -> new DoorDto()
                        .mapEntityToDto(doorEntity))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoorDto> getFavoritesDoors() {
        return doorRepository.findAll().stream()
                .map(doorEntity -> new DoorDto()
                        .mapEntityToDto(doorEntity))
                .filter(DoorDto::isFavorites)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setDoorFavorite(String name) {
        for (DoorEntity door : doorRepository.findAll()) {
            if (door.getName().equals(name)) {
                door.setFavorites(true);
            }
        }
    }

    @Override
    @Transactional
    public void saveEntity(DoorEntity doorEntity) {
        doorRepository.save(doorEntity);
    }
}
