package thisApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thisApplication.model.entity.CameraEntity;
@Repository
public interface CameraRepository extends JpaRepository<CameraEntity, Integer> {
}
