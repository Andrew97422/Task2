package thisApplication.service.Impl;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thisApplication.model.dto.camera.CameraDto;
import thisApplication.model.dto.generics.BaseApi;
import thisApplication.model.entity.camera.CameraEntity;
import thisApplication.repository.CameraRepository;
import thisApplication.service.CameraService;
import thisApplication.service.CarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CameraServiceImpl implements CameraService {
    private final CameraRepository cameraRepository;
    @Override
    @Transactional(readOnly = true)
    public List<CameraEntity> getAll() {
        return cameraRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public @Nullable List<String> getRooms() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cars.cprogroup.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        CarService service = retrofit.create(CarService.class);

        // Calling '/api/rubetek/cameras'
        Call<BaseApi<CameraDto>> callCamera = service.getCameras();

        try {
            Response<BaseApi<CameraDto>> response = callCamera.execute();
            return Arrays.asList(response.body().getData().getRoom());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return List.of("");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraDto> getCamerasInRoom(String room) {
        return cameraRepository.findAllByRoom(room).stream()
                .map(new CameraDto()::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraDto> getFavoritesCameras() {
        return cameraRepository.findAllByFavoritesIsTrue().stream()
                .map(new CameraDto()::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setCameraFavorite(String name) {
        CameraEntity cameraEntity = cameraRepository.findCameraEntityByName(name);
        cameraEntity.setFavorites(true);
        cameraRepository.save(cameraEntity);
    }

    @Override
    @Transactional
    public void setCameraRec(String name) {
        CameraEntity cameraEntity = cameraRepository.findCameraEntityByName(name);
        cameraEntity.setRec(!cameraEntity.isRec());
        cameraRepository.save(cameraEntity);
    }

    @Override
    @Transactional
    public void saveEntity(CameraEntity cameraEntity) {
        cameraRepository.save(cameraEntity);
    }
}
