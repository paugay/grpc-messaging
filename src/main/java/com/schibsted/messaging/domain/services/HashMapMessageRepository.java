package com.schibsted.messaging.domain.services;

import com.schibsted.messaging.domain.models.InternalMessage;
import com.schibsted.messaging.domain.models.InternalMessageId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HashMapMessageRepository implements MessageRepository {
    private final Map<InternalMessageId, InternalMessage> map;

    public HashMapMessageRepository() {
        map = new HashMap();
    }

    @Override
    public List<InternalMessage> getMessageList() {
        return new ArrayList(map.values());
    }

    @Override
    public InternalMessage getMessage(InternalMessageId internalMessageId) {
        return map.get(internalMessageId);
    }

    @Override
    public InternalMessage createMessage(InternalMessage internalMessage) {
        return updateMessage(internalMessage);
    }

    @Override
    public InternalMessage updateMessage(InternalMessage internalMessage) {
        map.put(internalMessage.getInternalMessageId(), internalMessage);

        return map.get(internalMessage.getInternalMessageId());
    }

    @Override
    public InternalMessage deleteMessage(InternalMessageId internalMessageId) {
        return map.remove(internalMessageId);
    }
}
