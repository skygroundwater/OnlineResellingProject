package com.example.onlineresellingproject.service.impl;

import com.example.onlineresellingproject.OnlineResellingProjectApplication;
import com.example.onlineresellingproject.config.TestContainersConfig;
import com.example.onlineresellingproject.dto.ad.Ad;
import com.example.onlineresellingproject.dto.ad.Ads;
import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import com.example.onlineresellingproject.dto.ad.ExtendedAd;
import com.example.onlineresellingproject.dto.user.Role;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import com.example.onlineresellingproject.exceptions.NoAccessException;
import com.example.onlineresellingproject.exceptions.NotValidDataException;
import com.example.onlineresellingproject.exceptions.NotValidModelException;
import com.example.onlineresellingproject.mappers.AdMapper;
import com.example.onlineresellingproject.repository.AdEntityRepo;
import com.example.onlineresellingproject.repository.UserEntityRepo;
import com.example.onlineresellingproject.service.FilesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ActiveProfiles("online_reselling_tests")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineResellingProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdServiceImplTest extends TestContainersConfig {

    private final AdMapper adMapper = new AdMapper();

    @Mock
    private FilesService filesService;

    @Autowired
    private AdEntityRepo adEntityRepo;

    @Autowired
    private UserEntityRepo userEntityRepo;

    private AdServiceImpl adService;

    private final UserEntity userEntity = new UserEntity(1L, "pupsichka", "password",
            "Олег", "Метелев", "+7(904)510-05-96",
            Role.USER, "/images/users/753c8737-a9f4-46fe-b038-2e1990bdb492.jpeg");

    private final AdEntity adEntity1 = new AdEntity();

    private final AdEntity adEntity2 = new AdEntity();

    private final List<AdEntity> adEntities = List.of(adEntity1, adEntity2);

    {
        adEntities.forEach(adEntity -> {
            adEntity.setImage("sdsdadasd");
            adEntity.setPrice(123123);
            adEntity.setUser(userEntity);
            adEntity.setTitle("Титульник");
            adEntity.setCreatedAt(LocalDateTime.now());
        });
        adEntity1.setId(1L);
        adEntity2.setId(2L);
    }

    @BeforeEach
    void setUp() {
        synchronized (this) {
            adService = new AdServiceImpl(adEntityRepo, adMapper, filesService);
            userEntityRepo.save(userEntity);
            adEntityRepo.save(adEntity1);
            adEntityRepo.save(adEntity2);
        }
    }

    @Test
    void shouldReturnAdEntityAfterPost() {
        AdEntity testingEntity = adService.post(adEntity1);
        assertEquals(testingEntity, adEntity1);
    }


    @Test
    void shouldThrowNotValidModelExceptionAfterPost() {
        assertThrows(NotValidModelException.class, () -> adService.post(null));
    }

    @Test
    void shouldReturnAdEntityAfterGet() {
        AdEntity testingEntity = adService.get(adEntity1.getId());
        Assertions.assertEquals(testingEntity, adEntity1);
    }

    @Test
    void shouldThrowNotValidModelExceptionAfterPatch() {
        assertThrows(NotValidModelException.class, () -> adService.post(null));
    }

    @Test
    void shouldReturnAdEntityAfterGetById() {
        AdEntity testingEntity = adService.get(adEntity1.getId());
        Assertions.assertEquals(testingEntity, adEntity1);
    }

    @Test
    void shouldThrowNotValidDataExceptionAfterGet() {
        assertThrows(NotValidDataException.class, () -> adService.get(null));
    }

    @Test
    void shouldReturnAdsDTOAfterGetAds() {
        List<AdEntity> entities = adEntityRepo.findAll();
        Ads ads = Ads.builder()
                .results(entities
                        .stream()
                        .map(adMapper::mapToAd)
                        .collect(Collectors.toList()))
                .count(entities.size())
                .build();

        Ads testingAds = adService.getAds();

        assertEquals(ads, testingAds);
    }

    @Test
    void shouldCreateNewAdEntityAndReturnDTO() {
        CreateOrUpdateAd createOrUpdateAd = CreateOrUpdateAd.builder()
                .title("титульник третьего объявления")
                .price(14000)
                .description("Описание третьего объявления")
                .build();

        Path path = Paths.get("images/ads/390b6788-b980-4032-81ab-a18af434a336.webp");
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile mockFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        when(filesService.saveAdsImage(mockFile)).thenReturn("images/ads/390b6788-b980-4032-81ab-a18af434a336.webp");
        AdEntity adEntity3 = new AdEntity().setFieldsAndReturnEntity(userEntity, createOrUpdateAd, filesService.saveAdsImage(mockFile));
        adEntity3.setId(5L);
        Ad ad = adMapper.mapToAd(adEntity3);
        Ad testingAd = adService.create(userEntity, createOrUpdateAd, mockFile);
        assertEquals(ad, testingAd);
    }

    @Test
    void shouldDeleteAnyAdFromIntegratedDataBaseBecauseHeIsAdmin() {
        UserEntity preSavedUserEntity = new UserEntity();
        preSavedUserEntity.setId(3L);
        preSavedUserEntity.setUsername("tester");
        preSavedUserEntity.setRole(Role.ADMIN);
        UserEntity userForTest = userEntityRepo.save(preSavedUserEntity);
        String msg = String.format("Ad deleted by admin, %s", userForTest.getUsername());
        String testMessage = adService.deleteAd(userForTest, adEntity2.getId());
        assertEquals(msg, testMessage);
        userEntityRepo.deleteById(userForTest.getId());
    }

    @Test
    void shouldThrowNoAccessExceptionWhenUserWantsToDeleteNotHisAd() {
        UserEntity preSavedUserEntity = new UserEntity();
        preSavedUserEntity.setId(3L);
        preSavedUserEntity.setRole(Role.USER);
        UserEntity userForTest = userEntityRepo.save(preSavedUserEntity);
        Throwable throwable = assertThrows(NoAccessException.class, () -> adService.deleteAd(userForTest, adEntity1.getId()));
        assertEquals(throwable.getMessage(), "Вы не можете удалить объявление другого пользователя");
        userEntityRepo.deleteById(userForTest.getId());
    }

    @Test
    void shouldReturnUniqueMessageWhenUserDeleteSelfAd() {
        AdEntity adForTesting = new AdEntity();
        adForTesting.setId(4L);
        adForTesting.setUser(userEntity);
        adEntityRepo.save(adForTesting);
        String msg = String.format("Ad deleted by user, %s", userEntity.getUsername());
        String testingString = adService.deleteAd(userEntity, adForTesting.getId());
        assertEquals(msg, testingString);
    }

    @Test
    void shouldThrowNoValidDataExceptionWhenDeleteByNull() {
        assertThrows(NotValidDataException.class, () -> adService.delete(null));
    }

    @Test
    void shouldReturnAdEntityWhenPatch() {
        AdEntity adForTesting = adService.patch(adEntity1);
        assertEquals(adForTesting, adEntity1);
    }

    @Test
    void shouldThrowNotValidModelExceptionWhenPatchNull() {
        assertThrows(NotValidModelException.class, () -> adService.patch(null));
    }

    @Test
    void shouldReturnExtendedAdDTO() {
        ExtendedAd extendedAd = adMapper.mapToExtendedAd(adEntity1);

        ExtendedAd testingExtendedAd = adService.getExtendedAd(adEntity1.getId());
        assertEquals(extendedAd, testingExtendedAd);
    }

    @Test
    void shouldReturnUpdatedAd() {
        CreateOrUpdateAd createOrUpdateAd = CreateOrUpdateAd.builder()
                .title("титульник первого объявления обновленный")
                .price(13000)
                .description("Описание первого объявления изменено")
                .build();
        AdEntity adEntity = new AdEntity();
        adEntity.setId(adEntity1.getId());
        adEntity.setUser(userEntity);
        adEntity.setImage(adEntity1.getImage());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setCreatedAt(adEntity1.getCreatedAt());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        Ad ad = adMapper.mapToAd(adEntity);
        Ad testingAd = adService.updateAd(adEntity1.getId(), createOrUpdateAd);
        assertEquals(ad, testingAd);
    }

    @Test
    void shouldReturnAdAfterUpdateImage() {

        Path path = Paths.get("images/ads/1e15f386-7c05-4e1d-8816-f412b3372c8a.webp");
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        MultipartFile mockFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        when(filesService.saveAdsImage(mockFile)).thenReturn("images/ads/1e15f386-7c05-4e1d-8816-f412b3372c8a.webp");
        AdEntity adEntity = adEntityRepo.findById(adEntity1.getId()).get();
        adEntity.setImage(filesService.saveAdsImage(mockFile));
        Ad ad = adMapper.mapToAd(adEntity);
        Ad testingAd = adService.updateImage(adEntity1.getId(), userEntity, mockFile);
        assertEquals(ad, testingAd);
    }

    @Test
    void shouldReturnUserAds() {
        Ads ads = adMapper.mapToAds(adEntityRepo.findAdEntitiesByUser(userEntity));
        Ads testingAds = adService.findUserAds(userEntity);
        assertEquals(ads, testingAds);
    }
}