package com.schibsted.messaging.domain.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Builder
public class InternalMessageId {
    @NonNull
    private String id;

    public InternalMessageId(String id) {
        this.id = id;
    }
}
