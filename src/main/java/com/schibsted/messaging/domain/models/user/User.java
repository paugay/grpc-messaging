package com.schibsted.messaging.domain.models.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Builder
public class User {
    @NonNull
    private UserId userId;
    @NonNull
    private String name;
    @NonNull
    private String email;

    public User(UserId userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
