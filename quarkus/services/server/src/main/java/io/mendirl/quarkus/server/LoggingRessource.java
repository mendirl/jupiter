package io.mendirl.quarkus.server;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationScoped
@Path("/logging")
public class LoggingRessource {

    private static final Logger LOG = Logger.getLogger(LoggingRessource.class);

    @GET
    public void log() {
        LOG.info("Some useful log message");
    }
}
