package com.schibsted.messaging.server.http.resources;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/simple")
@Service
public class SimpleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(@QueryParam(value = "name") String name) {
        return new String("Hi " + name);
    }
}