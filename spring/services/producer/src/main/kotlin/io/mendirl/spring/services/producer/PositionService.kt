package io.mendirl.spring.services.producer

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.security.SecureRandom
import java.time.Instant

@Service
class PositionService {

    fun create(): Mono<Position> =
        Position("position ${SecureRandom.getInstanceStrong().nextInt() % 1000}", Instant.now()).toMono()
}
