package io.mendirl.spring.services.producer

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.kotlin.core.publisher.toMono
import java.time.Instant


/**
 * Due to the test nature with @WebFluxTest,
 * the security is not the one defined in ProducerSecurityConfiguration.
 * The default security config is done. (basic)
 */
@WebFluxTest(excludeAutoConfiguration = [ReactiveOAuth2ResourceServerAutoConfiguration::class])
@Import(value = [WebController::class])
@ActiveProfiles(value = ["test"])
class WebControllerTest {

    @MockBean
    lateinit var positionService: PositionService

    @Autowired
    lateinit var testClient: WebTestClient

    @Test
    @WithMockUser
    fun `test 1`() {
        given(positionService.create())
            .willReturn(Position("position 123456", Instant.now()).toMono())

        testClient
            .get()
            .uri("/api/position")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("@.name").isEqualTo("position 123456")
            .jsonPath("@.date").isNotEmpty
    }
}
