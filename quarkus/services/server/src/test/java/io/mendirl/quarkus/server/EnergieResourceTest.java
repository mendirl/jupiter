package io.mendirl.quarkus.server;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
//@QuarkusTestResource(WiremockEnergie.class)
class EnergieResourceTest {

    @Test
    void testCountryNameEndpoint() {
        given()
            .when().get("/data/energie/year")
            .then()
            .statusCode(200).contentType("application/zip")
           ;
    }

}
