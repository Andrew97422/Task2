package thisApplication.model.dto.door;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import thisApplication.model.dto.base.BaseDto;
import thisApplication.model.entity.door.DoorEntity;

@Schema(description = "Дверь")
@AllArgsConstructor
//@NoArgsConstructor
@Data
@SuperBuilder
public class DoorDto extends BaseDto {
    public DoorEntity mapDtoToEntity() {
        return DoorEntity.builder()
                .id(getId())
                .name(getName())
                .snapshot(getSnapshot())
                .room(getRoom())
                .favorites(isFavorites())
                .build();
    }

    public DoorDto mapEntityToDto(DoorEntity doorEntity) {
        return DoorDto.builder()
                .favorites(doorEntity.isFavorites())
                .name(doorEntity.getName())
                .id(doorEntity.getId())
                .snapshot(doorEntity.getSnapshot())
                .room(doorEntity.getRoom())
                .build();
    }
}