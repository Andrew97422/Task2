package thisApplication.model.dto;

import lombok.Data;

@Data
public class CameraApi {
    private boolean success;
    @Data
    public static class DataCameraApi {
        private String[] room;
        private CameraDto[] cameras;
    }

    private DataCameraApi data;
}