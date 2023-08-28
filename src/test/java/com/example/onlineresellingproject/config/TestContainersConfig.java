package com.example.onlineresellingproject.config;

import com.example.onlineresellingproject.containers.PostgresqlTestContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainersConfig {

    @Container
    public static PostgreSQLContainer container = PostgresqlTestContainer.getInstance();


}
