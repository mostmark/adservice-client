package com.test;

import hipstershop.stubs.Ad;
import hipstershop.stubs.AdRequest;
import hipstershop.stubs.AdResponse;
import hipstershop.stubs.AdServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.logging.Logger;

@Path("/ads")
public class AdServiceClientResource {

    private static final Logger LOG = Logger.getLogger(AdServiceClientResource.class.getName());

    @GrpcClient
    AdServiceGrpc.AdServiceBlockingStub adservice;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{context}")
    public String hello(@PathParam("context") String context) {
        LOG.info("Received request! Context = " + context);

        String message = "";

        AdRequest request = AdRequest.newBuilder().addContextKeys(context).build();
        AdResponse response;

        response = adservice.getAds(request);

        for (Ad ads : response.getAdsList()) {
            message = message + " " + ads.getText();
        }
        return message;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRandom() {
        LOG.info("Received request! No context parameter provided");

        String message = "";

        AdRequest request = AdRequest.newBuilder().build();
        AdResponse response;

        response = adservice.getAds(request);

        for (Ad ads : response.getAdsList()) {
            message = message + " " + ads.getText();
        }
        return message;
    }
}
