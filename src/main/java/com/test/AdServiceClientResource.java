package com.test;

import hipstershop.stubs.Ad;
import hipstershop.stubs.AdRequest;
import hipstershop.stubs.AdResponse;
import hipstershop.stubs.AdServiceGrpc;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/ads")
public class AdServiceClientResource {

    @GrpcClient
    AdServiceGrpc.AdServiceBlockingStub adservice;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{context}")
    public String hello(@PathParam("context") String context) {
        System.out.println("Received request! Context = " + context);

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
        System.out.println("Received request! No context parameter provided");

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
