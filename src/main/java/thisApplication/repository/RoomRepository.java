package thisApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thisApplication.model.entity.room.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, String> {
}
