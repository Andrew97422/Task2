package thisApplication.model.dto.camera;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import thisApplication.model.dto.base.BaseDto;
import thisApplication.model.entity.camera.CameraEntity;

@Schema(description = "Камера")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CameraDto extends BaseDto {
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
