package io.mendirl.spring.services.consumer

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import reactor.test.StepVerifier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    ids = ["io.mendirl:spring-producer:+:stubs:7072"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@Disabled
class ProducerClientTest {

    @Autowired
    lateinit var client: ProducerClient


    @Test
    fun `test 1`() {
        val position = client.position()
        StepVerifier.create(position)
            .expectNextMatches {
                it.name == "position 12345"
            }
            .verifyComplete()

    }


}
