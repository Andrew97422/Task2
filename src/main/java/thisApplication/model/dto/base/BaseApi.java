package thisApplication.model.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseApi<T extends BaseDto> {
    private boolean success;
}
