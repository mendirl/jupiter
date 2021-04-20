package io.mendirl.spring.services.producer

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class WebController(val positionService: PositionService) {

    private val logger = LoggerFactory.getLogger(ProducerApplication::class.java)

    @GetMapping("/api/position")
    fun position(): Mono<Position> =
        positionService.create()
            .log()

}

