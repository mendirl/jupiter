package io.mendirl.quarkus.services.consumer;

import io.quarkus.oidc.client.filter.OidcClientFilter;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/position")
@OidcClientFilter
@RegisterRestClient(configKey = "producer-api")
public interface ProducerClient {

    @GET
    Position position();
}


