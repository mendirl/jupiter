package io.mendirl.services.common.test.contract

import dasniko.testcontainers.keycloak.KeycloakContainer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles(value = ["test"])
class ContractBaseTest {

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
}
