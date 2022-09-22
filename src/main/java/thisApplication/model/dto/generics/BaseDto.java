package thisApplication.model.dto.generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDto {
    String name;
    String snapshot;
    String room;
    int id;
    boolean favorites;
}
