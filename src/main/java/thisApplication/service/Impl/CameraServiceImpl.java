package thisApplication.service.Impl;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thisApplication.model.dto.CameraApi;
import thisApplication.model.dto.CameraDto;
import thisApplication.model.entity.CameraEntity;
import thisApplication.repository.CameraRepository;
import thisApplication.service.CameraService;
import thisApplication.service.CarService;

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
    public String[] getRooms() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cars.cprogroup.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        CarService service = retrofit.create(CarService.class);

        // Calling '/api/rubetek/cameras'
        Call<CameraApi> callCamera = service.getCameras();

        try {
            Response<CameraApi> response = callCamera.execute();
            assert response.body() != null;
            return response.body().getData().getRoom();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraDto> getCamerasInRoom(String room) {
        return cameraRepository.findAll().stream()
                .map(cameraEntity -> new CameraDto()
                        .mapEntityToDto(cameraEntity))
                .filter(cameraDto -> cameraDto.getRoom() != null)
                .filter(cameraDto -> cameraDto.getRoom().equals(room))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CameraDto> getFavoritesCameras() {
        return cameraRepository.findAll().stream()
                .map(cameraEntity -> new CameraDto()
                        .mapEntityToDto(cameraEntity))
                .filter(CameraDto::isFavorites)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setCameraFavorite(String name) {
        for (CameraEntity camera : cameraRepository.findAll()) {
            if (camera.getName().equals(name)) {
                camera.setFavorites(true);
            }
        }
    }

    @Override
    @Transactional
    public void setCameraRec(String name) {
        for (CameraEntity camera : cameraRepository.findAll()) {
            camera.setRec(camera.getName().equals(name) && !camera.isRec());
        }
    }

    @Override
    @Transactional
    public void saveEntity(CameraEntity cameraEntity) {
        cameraRepository.save(cameraEntity);
    }
}
