package thisApplication.service;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.GET;
import thisApplication.model.dto.camera.CameraApi;
import thisApplication.model.dto.door.DoorApi;

@Service
public interface CarService {
    @GET("/api/rubetek/cameras")
    Call<CameraApi> getCameras();

    @GET("/api/rubetek/doors")
    Call<DoorApi> getDoors();
}