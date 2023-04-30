package io.mendirl.quarkus.services.server

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/greeting")
@Produces(MediaType.TEXT_PLAIN)
class GreetingResource {

    @GET
    fun greeting() = Response.ok("HELLO FROM QUARKUS").build()
}
