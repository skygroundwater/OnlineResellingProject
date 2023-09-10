package com.example.onlineresellingproject.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilesServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);

    FilesServiceImpl filesService = new FilesServiceImpl();
    MultipartFile mockFile;
    File newFile;
    Path path = Paths.get("images/ads/390b6788-b980-4032-81ab-a18af434a336.webp");
    String name = "390b6788-b980-4032-81ab-a18af434a336.webp";
    byte[] content = null;

    @BeforeEach
    public void getFiles() {
        newFile = new File("src/test/resources/images/newTestFile.png");
    }

    @Test
    void shouldSaveUserImageAndReturnNewFilePath() throws NoSuchFieldException, IllegalAccessException {
        try {
            content = Files.readAllBytes(Path.of("src/test/resources/images/newTestFile.png"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        mockFile = new MockMultipartFile("newTestFile.png", content);

        Field imagesPath = FilesServiceImpl.class.getDeclaredField("imagesPath");
        imagesPath.setAccessible(true);
        imagesPath.set(filesService, "src/test/resources/images");

        Field usersImagesPath = FilesServiceImpl.class.getDeclaredField("usersImagesPath");
        usersImagesPath.setAccessible(true);
        usersImagesPath.set(filesService, "/users");

        String result = filesService.saveUserImage(mockFile);

        String[] split = mockFile.getName().split("\\.");
        String extension = split[split.length - 1];

        assertEquals(extension, "png");
        assertTrue(result.contains("/users"));
    }

    @Test
    void shouldSaveAdsImageAndReturnNewFilePath() throws NoSuchFieldException, IllegalAccessException {
        try {
            content = Files.readAllBytes(Path.of("src/test/resources/images/newTestFile.png"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        mockFile = new MockMultipartFile("newTestFile.png", content);

        Field imagesPath = FilesServiceImpl.class.getDeclaredField("imagesPath");
        imagesPath.setAccessible(true);
        imagesPath.set(filesService, "src/test/resources/images");

        Field usersImagesPath = FilesServiceImpl.class.getDeclaredField("adsImagesPath");
        usersImagesPath.setAccessible(true);
        usersImagesPath.set(filesService, "/ads");

        String result = filesService.saveAdsImage(mockFile);

        String[] split = mockFile.getName().split("\\.");
        String extension = split[split.length - 1];

        assertEquals(extension, "png");
        assertTrue(result.contains("/ads"));
    }

    @Test
    void shouldSetNewFileName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = FilesServiceImpl.class.getDeclaredMethod("getNewFileName", MultipartFile.class);
        method.setAccessible(true);

        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        mockFile = new MockMultipartFile(name, name, MediaType.MULTIPART_FORM_DATA_VALUE, content);

        String invoke = (String) method.invoke(filesService, mockFile);
        String[] split = invoke.split("\\.");
        String extension = split[split.length - 1];

        assertEquals(extension, "webp");
        assertNotEquals("390b6788-b980-4032-81ab-a18af434a336", method.invoke(filesService, mockFile));
    }


    @Test
    void shouldUploadFileToServer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        Method method = FilesServiceImpl.class.getDeclaredMethod("uploadFile", MultipartFile.class, File.class);
        method.setAccessible(true);

        try {
            content = Files.readAllBytes(Path.of("src/test/resources/images/newTestFile.png"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        mockFile = new MockMultipartFile("mockFile", content);
        mockFile.transferTo(new File("src/test/resources/images/newTestFile2.png"));
        method.invoke(filesService, mockFile, newFile);

        assertEquals(mockFile.getBytes().length, newFile.length());
    }
}
