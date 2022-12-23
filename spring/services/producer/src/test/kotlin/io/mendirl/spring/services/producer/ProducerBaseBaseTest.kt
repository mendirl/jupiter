package io.mendirl.spring.services.producer

import io.mendirl.services.common.test.contract.ContractBaseTest
import io.restassured.RestAssured
import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeEach
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.kotlin.core.publisher.toMono
import java.time.Instant

class ProducerBaseBaseTest : ContractBaseTest() {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var testClient: WebTestClient

    @MockBean
    private lateinit var positionService: PositionService

    @BeforeEach
    fun setUp() {
        RestAssured.baseURI = "http://localhost:$port"
        RestAssuredWebTestClient.webTestClient(testClient)
        given(positionService.create())
            .willReturn(Position("position 23512", Instant.now()).toMono())
    }

}
