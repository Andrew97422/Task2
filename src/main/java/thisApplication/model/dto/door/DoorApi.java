package thisApplication.model.dto.door;

import lombok.Getter;
import lombok.Setter;
import thisApplication.model.dto.base.BaseApi;

import java.util.List;

@Getter
@Setter
public class DoorApi extends BaseApi {
    private List<DoorDto> data;
}
