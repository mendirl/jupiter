package io.mendirl.spring.services.producer;

import io.mendirl.services.common.test.contract.ContractBaseTest;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;


@Disabled
class ProducerContractBaseTest extends ContractBaseTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient testClient;

//    @MockBean
//    private PositionService positionService;
//
//    @BeforeEach
//    public void setUp() {
//        RestAssured.baseURI = "http://localhost:" + this.port;
//
//        RestAssuredWebTestClient.webTestClient(this.testClient);
//
//        Mockito.when(this.positionService.create())
//            .thenReturn(Mono.just(new Position("position 23512", Instant.now())));
//    }
//
//    @Test
//    @Disabled("duplicated from ContractVerifierTest")
//    void test() {
//        // given:
//        WebTestClientRequestSpecification request = given()
//            .header("Content-Type", "application/json");
//
//        // when:
//        WebTestClientResponse response = given().spec(request)
//            .get("/api/position");
//
//        // then:
//        assertThat(response.statusCode()).isEqualTo(200);
//        assertThat(response.header("Content-Type")).matches("application/json.*");
//        // and:
//        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
//        assertThat(parsedJson.read("$.name", String.class)).matches("position [0-9]{5}");
//        assertThat(parsedJson.read("$.date", String.class)).matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]*Z");
//
//    }

}
