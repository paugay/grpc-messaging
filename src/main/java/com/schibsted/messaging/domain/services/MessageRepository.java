package com.schibsted.messaging.domain.services;

import com.schibsted.messaging.domain.models.InternalMessage;
import com.schibsted.messaging.domain.models.InternalMessageId;

import java.util.List;

public interface MessageRepository {
    List<InternalMessage> getMessageList();

    InternalMessage getMessage(InternalMessageId internalMessageId);

    InternalMessage createMessage(InternalMessage internalMessage);

    InternalMessage updateMessage(InternalMessage internalMessage);

    InternalMessage deleteMessage(InternalMessageId internalMessageId);
}
