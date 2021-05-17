package io.mendirl.spring.services.consumer

import dasniko.testcontainers.keycloak.KeycloakContainer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.test.StepVerifier

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    ids = ["io.mendirl:spring-producer:+:stubs:7072"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@ActiveProfiles("test")
class ProducerClientTest {

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

    @Autowired
    lateinit var client: ProducerClient

    @Test
    fun `test ok`() {
        val position = client.position()
        StepVerifier.create(position)
            .expectNextMatches {
                it.name == "position 12345"
            }
            .verifyComplete()
    }


}
