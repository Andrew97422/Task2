package thisApplication.service;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.dto.door.DoorDto;
import thisApplication.model.dto.generics.BaseApi;
import thisApplication.model.dto.generics.BaseDto;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrofitService<T extends BaseDto> {
    public @Nullable List<T> response() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cars.cprogroup.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        CarService service = retrofit.create(CarService.class);

        Call<BaseApi<T>> call;


        /*switch (T) {
            case CameraDto:
                Call<BaseApi<CameraDto>> callCamera = service.getCameras();
                Response<BaseApi<CameraDto>> response1;
                try {
                    response1 = callCamera.execute();
                    return response1.body().getData();
                } catch (IOException e) {throw new RuntimeException(e);}
            case "DoorDto":
                Call<BaseApi<DoorDto>> callDoor = service.getDoors();
                Response<BaseApi<DoorDto>> response2;
                try {
                    response2 = callDoor.execute();
                    return response2.body().getData();
                } catch (IOException e) {throw new RuntimeException(e);}
        }*/
        return List.of();
    }
}
