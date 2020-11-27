package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.containers.MySQLContainer.IMAGE;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration_test")
@Testcontainers
@Slf4j
class WidgetServiceTest {

    @Container
    static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer(DockerImageName.parse(MySQLContainer.NAME))
        .withLogConsumer(new Slf4jLogConsumer(log));

    @Autowired
    private WidgetService widgetService;

    @Test
    void saveAndRetrieveWidget() {

        final WidgetEntity newWidget = new WidgetEntity(null, "Martin's Widget");

        final WidgetEntity savedWidget = widgetService.saveWidget(newWidget);

        assertThat(savedWidget.getId()).isNotNull();

        final WidgetEntity foundWidget = widgetService.findWidget(savedWidget.getId());

        assertThat(foundWidget).isEqualTo(savedWidget);

    }

}
