package com.schibsted.messaging.server.http.config;

import com.schibsted.messaging.server.http.resources.ItemsResource;
import com.schibsted.messaging.server.http.resources.MessagesResource;
import com.schibsted.messaging.server.http.resources.SimpleResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyResourceConfig extends ResourceConfig {

    public JerseyResourceConfig() {
        register(SimpleResource.class);
        register(ItemsResource.class);
        register(MessagesResource.class);
    }
}
