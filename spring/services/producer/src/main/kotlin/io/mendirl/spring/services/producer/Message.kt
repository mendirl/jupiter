package io.mendirl.spring.services.producer

import java.time.Instant

data class Message(
    val origin: String,
    val interaction: String,
    val index: Long = 0,
    val created: Long = Instant.now().epochSecond
)
