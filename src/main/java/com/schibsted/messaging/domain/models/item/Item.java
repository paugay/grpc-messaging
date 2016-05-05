package com.schibsted.messaging.domain.models.item;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Builder
public class Item {
    @NonNull
    private String type;
    @NonNull
    private String id;

    public Item(String type, String id) {
        this.type = type;
        this.id = id;
    }
}