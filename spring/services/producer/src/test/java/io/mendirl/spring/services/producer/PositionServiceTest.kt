package io.mendirl.spring.services.producer

import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import java.time.Instant

class PositionServiceTest {

    private val positionService = PositionService()

    @Test
    fun `test 1`() {
        val position = positionService.create()

        StepVerifier.create(position)
            .expectNextMatches {
                it.name.startsWith("position ")
                it.date.isAfter(Instant.MIN)
            }
            .verifyComplete()
    }
}
