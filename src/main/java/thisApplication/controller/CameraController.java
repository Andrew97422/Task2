package thisApplication.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.service.CameraService;

import java.util.List;

@RequiredArgsConstructor
@Validated
@Tag(name = "Camera", description = "Камера")
@RestController
@RequestMapping("/cameras")
public class CameraController {
    private final CameraService cameraService;

    @GetMapping("/rooms")
    public List <String> getAllRooms() {
        return cameraService.getRooms();
    }

    @GetMapping("/cameras/{room}")
    public List<CameraDto> getCamerasInRoom(
            @PathVariable("room") String room) {
        return cameraService.getCamerasInRoom(room);
    }

    @GetMapping("/favorite")
    public List<CameraDto> getFavoriteCameras() {
        return cameraService.getFavoritesCameras();
    }

    @PatchMapping("/favorite/{name}")
    public void updateF(@PathVariable("name") String name) {
        cameraService.setCameraFavorite(name);
    }

    @PatchMapping("/rec/{name}")
    public void updateR(@PathVariable("name") String name) {
        cameraService.setCameraRec(name);
    }
}
