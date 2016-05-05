package com.schibsted.messaging.domain.models;

import com.schibsted.messaging.domain.models.item.Item;
import com.schibsted.messaging.domain.models.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Builder
public class InternalMessage {
    @NonNull
    private InternalMessageId internalMessageId;
    @NonNull
    private User userFrom;
    @NonNull
    private User userTo;
    @NonNull
    private Item item;
    @NonNull
    private Content content;
    @NonNull
    private long timestamp;

    public InternalMessage(
            InternalMessageId internalMessageId,
            User userFrom,
            User userTo,
            Item item,
            Content content,
            long timestamp) {
        this.internalMessageId = internalMessageId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.item = item;
        this.content = content;
        this.timestamp = timestamp;
    }
}
