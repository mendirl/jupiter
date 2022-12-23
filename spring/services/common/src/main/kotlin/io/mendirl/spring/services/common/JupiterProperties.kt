package io.mendirl.spring.services.common

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jupiter")
data class JupiterProperties @ConstructorBinding constructor(
    val security: Security,
    val applications: Map<String, Application>
) {
    data class Application(
        val name: String,
        val port: Int,
        val url: String
    )

    data class Security(
        val oauth2: Oauth2
    ) {
        data class Oauth2(val url: String, val userNameAttribute: String)
    }
}
