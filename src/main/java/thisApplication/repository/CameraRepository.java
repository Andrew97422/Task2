package thisApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thisApplication.model.entity.camera.CameraEntity;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<CameraEntity, Integer> {
    List<CameraEntity> findAllByRoom(String room);
    List<CameraEntity> findAllByFavoritesIsTrue();
    CameraEntity findCameraEntityByName(String name);
}
