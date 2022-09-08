package thisApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thisApplication.model.entity.DoorEntity;
@Repository
public interface DoorRepository extends JpaRepository<DoorEntity, Integer> {
}
