package thisApplication.model.dto.camera;

import lombok.Getter;
import lombok.Setter;
import thisApplication.model.dto.room.RoomDto;

import java.util.List;

@Getter
@Setter
public class CameraDataApi {
    private List<String> room;
    private List<CameraDto> cameras;
}
