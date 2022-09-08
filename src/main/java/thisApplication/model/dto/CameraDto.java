package thisApplication.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import thisApplication.model.entity.CameraEntity;

@Schema(description = "Камера")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CameraDto {
    @Schema(description = "Название камеры")
    private String name;

    @Schema(description = "Снимок")
    private String snapshot;

    @Schema(description = "Название комнаты")
    private String room;

    @Schema(description = "Id камеры")
    private int id;

    @Schema(description = "Избранное")
    private boolean favorites;

    @Schema(description = "Запись")
    private boolean rec;

    public CameraDto mapEntityToDto(@NotNull CameraEntity cameraEntity) {
        return CameraDto.builder()
                .id(cameraEntity.getId())
                .snapshot(cameraEntity.getSnapshot())
                .name(cameraEntity.getName())
                .room(cameraEntity.getRoom())
                .favorites(cameraEntity.isFavorites())
                .rec(cameraEntity.isRec())
                .build();
    }

    public CameraEntity mapDtoToEntity() {
        return CameraEntity.builder()
                .id(getId())
                .name(getName())
                .room(getRoom())
                .snapshot(getSnapshot())
                .build();
    }
}
