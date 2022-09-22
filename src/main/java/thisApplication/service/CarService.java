package thisApplication.service;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.GET;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.dto.door.DoorDto;
import thisApplication.model.dto.generics.BaseApi;
import thisApplication.model.dto.generics.BaseDto;

@Service
public interface CarService {
    @GET("/api/rubetek/cameras")
    Call<BaseApi<CameraDto>> getCameras();

    @GET("/api/rubetek/doors")
    Call<BaseApi<DoorDto>> getDoors();
}