package thisApplication.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thisApplication.model.entity.DoorEntity;

@Schema(description = "Дверь")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoorDto {
    @Schema(description = "Название двери")
    String name;

    @Schema(description = "Снимок")
    String snapshot;

    @Schema(description = "Комната")
    String room;

    @Schema(description = "Id двери")
    int id;

    @Schema(description = "Избранное")
    boolean favorites;

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