package com.example.onlineresellingproject.repository;

import com.example.onlineresellingproject.OnlineResellingProjectApplication;
import com.example.onlineresellingproject.config.TestContainersConfig;
import com.example.onlineresellingproject.dto.user.Role;
import com.example.onlineresellingproject.entity.AdEntity;
import com.example.onlineresellingproject.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("online_reselling_tests")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineResellingProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdEntityRepoTest extends TestContainersConfig {

    @Autowired
    private AdEntityRepo adEntityRepo;

    @Autowired
    private UserEntityRepo userEntityRepo;

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
        userEntityRepo.save(userEntity);
        adEntityRepo.save(adEntity1);
        adEntityRepo.save(adEntity2);
    }

    @Test
    void shouldReturnListOfAdEntities() {
        List<AdEntity> adEntities = adEntityRepo.findAll();
        assertEquals(2, adEntities.size());
    }

}
