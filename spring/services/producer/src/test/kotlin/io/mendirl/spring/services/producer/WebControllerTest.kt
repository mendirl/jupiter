package io.mendirl.spring.services.producer

import dasniko.testcontainers.keycloak.KeycloakContainer
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.kotlin.core.publisher.toMono
import java.time.Instant

@Disabled
@WebFluxTest
@Testcontainers
@ContextConfiguration(
    classes = [WebController::class, ProducerSecurityConfiguration::class]
)
@ActiveProfiles(value = ["test"])
class WebControllerTest {

    companion object {
        @Container
        private val keycloak =
            KeycloakContainer().withRealmImportFile("realm-jupiter.json")

        @DynamicPropertySource
        @JvmStatic
        fun registerPgProperties(registry: DynamicPropertyRegistry) {
            registry.add("jupiter.security.oauth2.url") {
                "http://localhost:${keycloak.httpPort}/auth/realms/JupiterR"
            }
        }
    }

    @MockitoBean
    lateinit var positionService: PositionService

    @Autowired
    lateinit var testClient: WebTestClient

    @Test
    @WithMockUser(authorities = ["SCOPE_position"])
    fun `test identified user with good scope`() {
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

    @Test
    @WithMockUser
    fun `test identified user with wrong scope`() {
        testClient
            .get()
            .uri("/api/position")
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    @WithAnonymousUser
    fun `test anonymous user`() {
        testClient
            .get()
            .uri("/api/position")
            .exchange()
            .expectStatus().is4xxClientError
    }

}
