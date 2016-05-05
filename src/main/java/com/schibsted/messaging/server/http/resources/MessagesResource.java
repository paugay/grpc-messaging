package com.schibsted.messaging.server.http.resources;

import com.schibsted.messaging.domain.models.InternalMessage;
import com.schibsted.messaging.domain.models.InternalMessageId;
import com.schibsted.messaging.domain.services.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResource implements MessageRepository {
    private final MessageRepository messageRepository;

    @Autowired
    public MessagesResource(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GET
    @Override
    public List<InternalMessage> getMessageList() {
        return messageRepository.getMessageList();
    }

    @GET
    @Path("/{messageId}")
    @Override
    public InternalMessage getMessage(@PathParam("messageId") InternalMessageId internalMessageId) {
        return messageRepository.getMessage(internalMessageId);
    }

    @POST
    @Override
    public InternalMessage createMessage(InternalMessage internalMessage) {
        return messageRepository.createMessage(internalMessage);
    }

    @PUT
    @Override
    public InternalMessage updateMessage(InternalMessage internalMessage) {
        return messageRepository.updateMessage(internalMessage);
    }

    @DELETE
    @Path("/{messageId}")
    @Override
    public InternalMessage deleteMessage(InternalMessageId internalMessageId) {
        return messageRepository.deleteMessage(internalMessageId);
    }
}