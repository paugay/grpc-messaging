package com.schibsted.messaging.domain.mappers.grpc;

import com.schibsted.messaging.domain.models.Content;
import com.schibsted.messaging.server.grpc.ContentProto;

public class ContentGrpcMapper {

    public static Content map(ContentProto contentProto) {
        return new Content(
                contentProto.getTitle(),
                contentProto.getBody());
    }

    public static ContentProto unmap(Content content) {
        return ContentProto.newBuilder()
                .setTitle(content.getTitle())
                .setBody(content.getBody())
                .build();
    }
}
