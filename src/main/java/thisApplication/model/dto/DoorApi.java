package thisApplication.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoorApi {
    private boolean success;
    private DoorDto[] data;
}
