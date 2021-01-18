package io.mendirl.quarkus.server;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/data")
public class EnergieResource {

    @Inject
    @RestClient
    Eco2MixClient client;

    @GET
    @Path("/energie/year")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> energieByYear() {
        var energieByYear = client.getEnergieByYear();
        energieByYear.subscribe().with(System.out::println);
        return Uni.createFrom().item("energieByYear");
    }

    @GET
    @Path("/energie/month")
    public Uni<String> energieByMonth() {
        var energieByYear = client.getEnergieByMonth();
        return Uni.createFrom().item("energieByMonth");
    }

}
