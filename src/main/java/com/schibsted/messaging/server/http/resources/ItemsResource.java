package com.schibsted.messaging.server.http.resources;

import com.schibsted.messaging.domain.models.item.Item;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/items")
@Service
public class ItemsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item sayHello() {
        return new Item("type", "id");
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item sayHello(@PathParam("itemId") String itemId) {
        return new Item("type", itemId);
    }

    @POST
//    @Path("/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item postHello(Item item) {
        return new Item(item.getType(), item.getId());
    }

}