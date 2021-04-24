package io.mendirl.spring.services.producer;

import io.mendirl.services.common.test.BaseTestClass;
import io.restassured.RestAssured;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Instant;


public class ProducerBaseTestClass extends BaseTestClass {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private PositionService positionService;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + this.port;

        RestAssuredWebTestClient.webTestClient(this.testClient);


        Mockito.when(this.positionService.create())
            .thenReturn(Mono.just(new Position("position 23512", Instant.now())));
    }
}
