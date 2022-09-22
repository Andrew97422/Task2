package thisApplication.model.dto.room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thisApplication.model.entity.room.RoomEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Комната")
public class RoomDto {
    @Schema(description = "Название комнаты")
    String name;
    public RoomEntity mapDtoToEntity() {
        return RoomEntity.builder().name(getName()).build();
    }

    public RoomDto mapEntityToDto(RoomEntity roomEntity) {
        return RoomDto.builder().name(roomEntity.getName()).build();
    }
}
