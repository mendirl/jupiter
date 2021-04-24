package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.HttpStatus
import org.springframework.http.MediaType

Contract.make {
    description "should return a position"
    request {
        method HttpMethods.GET
        url("/api/position")
    }
    response {
        status HttpStatus.OK
        headers {
            contentType MediaType.APPLICATION_JSON_VALUE
        }
        body([
            "name": $(regex('position [0-9]{5}')),
            "date": $(regex('[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]*Z')),
        ])
    }
}
