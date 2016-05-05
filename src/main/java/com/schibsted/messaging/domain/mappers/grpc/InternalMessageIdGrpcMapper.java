package com.schibsted.messaging.domain.mappers.grpc;

import com.schibsted.messaging.domain.models.InternalMessageId;
import com.schibsted.messaging.server.grpc.InternalMessageIdProto;

public class InternalMessageIdGrpcMapper {

    public static InternalMessageId map(InternalMessageIdProto internalMessageIdProto) {
        return new InternalMessageId(internalMessageIdProto.getId());
    }

    public static InternalMessageIdProto unmap(InternalMessageId internalMessageId) {
        return InternalMessageIdProto.newBuilder()
                .setId(internalMessageId.getId())
                .build();
    }
}
