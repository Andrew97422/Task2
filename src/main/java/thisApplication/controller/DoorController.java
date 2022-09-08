package thisApplication.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thisApplication.model.dto.DoorDto;
import thisApplication.service.DoorService;

import java.util.List;

@RequiredArgsConstructor
@Validated
@Tag(name = "Door", description = "Дверь")
@RestController
@RequestMapping("/doors")
public class DoorController {
    private final DoorService doorService;

    @GetMapping("/all_in_room/{room}")
    public List<DoorDto> getAllDoors(
            @PathVariable("room") String room) {
        return doorService.getDoorsInRoom(room);
    }

    @GetMapping("/get_favorite")
    public List<DoorDto> getFavoriteDoors() {
        return doorService.getFavoritesDoors();
    }

    @PatchMapping("/set_favorite/{name}")
    public void updateF(@PathVariable("name") String name) {
        doorService.setDoorFavorite(name);
    }
}
