package thisApplication.model.dto.camera;

import lombok.Getter;
import lombok.Setter;
import thisApplication.model.dto.base.BaseApi;

@Getter
@Setter
public class CameraApi extends BaseApi{
    CameraDataApi data;
}
