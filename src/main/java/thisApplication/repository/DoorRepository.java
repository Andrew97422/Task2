package thisApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thisApplication.model.entity.door.DoorEntity;

import java.util.List;

@Repository
public interface DoorRepository extends JpaRepository<DoorEntity, Integer> {
    List<DoorEntity> findDoorEntitiesByRoom(String room);
    List<DoorEntity> findAllByFavoritesIsTrue();
    DoorEntity findDoorEntityByName(String name);
}
