package io.mendirl.quarkus.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

public class WiremockEnergie implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();

        stubFor(get(urlEqualTo("/download/eco2mix/eCO2mix_RTE_energie_A.zip"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(
                    "[{" +
                        "\"name\": \"Ελλάδα\"," +
                        "\"capital\": \"Αθήνα\"" +
                        "}]"
                )));

        stubFor(get(urlMatching(".*"))
            .atPriority(10)
            .willReturn(aResponse().proxiedFrom("https://eco2mix.rte-france.com/")));

        return Collections.singletonMap("io.mendirl.quarkus.server.Eco2MixClient/mp-rest/url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
