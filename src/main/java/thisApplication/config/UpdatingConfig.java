package thisApplication.config;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thisApplication.model.dto.CameraApi;
import thisApplication.model.dto.CameraDto;
import thisApplication.model.dto.DoorApi;
import thisApplication.model.dto.DoorDto;
import thisApplication.model.entity.CameraEntity;
import thisApplication.model.entity.DoorEntity;
import thisApplication.service.CameraService;
import thisApplication.service.CarService;
import thisApplication.service.DoorService;

import java.io.IOException;
import java.util.List;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class UpdatingConfig {
    private final CameraService cameraService;
    private final DoorService doorService;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://cars.cprogroup.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    CarService service = retrofit.create(CarService.class);
    Call<CameraApi> callCamera = service.getCameras();
    Call<DoorApi> callDoor = service.getDoors();
    Response<CameraApi> response1;
    Response<DoorApi> response2;
    {
        try {
            response1 = callCamera.execute();
            response2 = callDoor.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    void create() {
        /*Обновление данных про камеры*/
        assert response1.body() != null;
        CameraDto[] cameras = response1.body().getData().getCameras();

        for (CameraDto camera : cameras) {
            CameraEntity cameraEntity = camera.mapDtoToEntity();
            cameraEntity.setRec(camera.isRec());
            cameraEntity.setFavorites(camera.isFavorites());
            cameraService.saveEntity(cameraEntity);
        }

        /*Обновление данных про двери*/
        // Calling '/api/rubetek/doors'
        assert response2.body() != null;
        DoorDto[] doors = response2.body().getData();

        for (DoorDto door : doors) {
            DoorEntity doorEntity = door.mapDtoToEntity();
            doorEntity.setFavorites(door.isFavorites());
            doorService.saveEntity(doorEntity);
        }
    }

    @Scheduled(fixedRateString = "${milliseconds}")
    void update() {
        /*Обновление данных про камеры*/
        // Calling '/api/rubetek/cameras'
        assert response1.body() != null;
        CameraDto[] cameras = response1.body().getData().getCameras();

        List<CameraEntity> cameraEntities = cameraService.getAll();

        for (CameraDto camera : cameras) {
            boolean has = false;
            for (CameraEntity cameraEntity : cameraEntities) {
                if (camera.getId() == cameraEntity.getId()) {
                    CameraEntity cameraEntity1 = camera.mapDtoToEntity();
                    cameraEntity1.setRec(cameraEntity.isRec());
                    cameraEntity1.setFavorites(cameraEntity.isFavorites());
                    cameraService.saveEntity(cameraEntity1);
                    has = true;
                    break;
                }
            }
            if (!has) {
                CameraEntity cameraEntity = camera.mapDtoToEntity();
                cameraEntity.setFavorites(camera.isFavorites());
                cameraEntity.setRec(camera.isRec());
                cameraService.saveEntity(cameraEntity);
            }
        }

        /*Обновление данных про двери*/
        // Calling '/api/rubetek/doors'
        assert response2.body() != null;
        DoorDto[] doors = response2.body().getData();

        List<DoorEntity> doorEntities = doorService.getAll();

        for (DoorDto door : doors) {
            boolean has = false;
            for (DoorEntity doorEntity : doorEntities) {
                if (door.getId() == doorEntity.getId()) {
                    DoorEntity doorEntity1 = door.mapDtoToEntity();
                    doorEntity1.setFavorites(doorEntity.isFavorites());
                    doorService.saveEntity(doorEntity1);
                    has = true;
                    break;
                }
            }
            if (!has) {
                DoorEntity doorEntity = door.mapDtoToEntity();
                doorEntity.setFavorites(door.isFavorites());
                doorService.saveEntity(doorEntity);
            }
        }
    }
}