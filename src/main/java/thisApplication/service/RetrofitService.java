package thisApplication.service;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thisApplication.model.dto.camera.CameraApi;
import thisApplication.model.dto.door.DoorApi;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrofitService {
    public @Nullable List response(String dto) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cars.cprogroup.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        CarService service = retrofit.create(CarService.class);

        switch (dto) {
            case "CameraDto" -> {
                Call<CameraApi> callCamera = service.getCameras();
                Response<CameraApi> response1;
                try {
                    response1 = callCamera.execute();
                    if (response1.body().getData().getCameras() == null) {
                        throw new NullPointerException();
                    }
                    return response1.body().getData().getCameras();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "DoorDto" -> {
                Call<DoorApi> callDoor = service.getDoors();
                Response<DoorApi> response2;
                try {
                    response2 = callDoor.execute();
                    if (response2.body().getData() == null) {
                        throw new NullPointerException();
                    }
                    return response2.body().getData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "RoomDto" -> {
                Call<CameraApi> callCamera = service.getCameras();
                Response<CameraApi> response3;
                try {
                    response3 = callCamera.execute();
                    if (response3.body().getData().getRoom() == null) {
                        throw new NullPointerException();
                    }
                    return response3.body().getData().getRoom();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return List.of();
    }
}
