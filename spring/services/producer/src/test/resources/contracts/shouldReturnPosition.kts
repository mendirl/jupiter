package contracts

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.http.MediaType

contract {
    ignored = true
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
                "date" to "2025-01-01T00:00:00.000Z"
            )
        )

        bodyMatchers {
            jsonPath("$.name", byRegex("position [0-9]{5}"))
            jsonPath("$.date", byRegex("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]*Z"))
        }
    }
}

