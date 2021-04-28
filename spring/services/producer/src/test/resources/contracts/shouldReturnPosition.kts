package contracts

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.http.MediaType
import java.time.Instant

contract {
    name = "should return a position"
    request {
        url = url("/api/position")
        method = GET
    }
    response {
        status = OK
        headers {
            contentType = MediaType.APPLICATION_JSON_VALUE
        }

        body = body(
            mapOf(
                "name" to "position 12345",
                "date" to Instant.now().toString()
            )
        )

        bodyMatchers {
            jsonPath("$.name", byRegex("position [0-9]{5}"))
            jsonPath("$.date", byRegex("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]*Z"))
        }
    }
}

