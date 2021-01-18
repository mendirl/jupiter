package io.mendirl.quarkus.server;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.GZIP;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.File;

@RegisterRestClient(configKey="eco2mix-api")
public interface Eco2MixClient {

    @GET
    @Path("/download/eco2mix/eCO2mix_RTE_energie_A.zip")
    @GZIP
    @Produces("application/zip")
    Uni<File> getEnergieByYear();

    @GET
    @Path("/download/eco2mix/eCO2mix_RTE_energie_M.zip")
    @GZIP
    @Produces("application/zip")
    Uni<String> getEnergieByMonth();


}
