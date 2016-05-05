package com.schibsted.messaging.domain.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Builder
public class Content {
    @NonNull
    private String title;
    @NonNull
    private String body;

    public Content(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
