package thisApplication.model.dto.generics;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseApi<T extends BaseDto> {
    private boolean success;
    private List<T> data;
}
