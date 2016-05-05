package com.schibsted.messaging.domain.models.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Builder
public class UserId {
    @NonNull
    private String id;

    public UserId(String id) {
        this.id = id;
    }
}
