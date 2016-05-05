package com.schibsted.messaging.domain.mappers.grpc;

import com.schibsted.messaging.domain.mappers.grpc.item.ItemGrpcMapper;
import com.schibsted.messaging.domain.mappers.grpc.user.UserGrpcMapper;
import com.schibsted.messaging.domain.models.InternalMessage;
import com.schibsted.messaging.server.grpc.InternalMessageProto;

public class InternalMessageGrpcMapper {

    public static InternalMessage map(InternalMessageProto internalMessageProto) {
        return new InternalMessage(
                InternalMessageIdGrpcMapper.map(internalMessageProto.getInternalMessageId()),
                UserGrpcMapper.map(internalMessageProto.getUserFrom()),
                UserGrpcMapper.map(internalMessageProto.getUserTo()),
                ItemGrpcMapper.map(internalMessageProto.getItem()),
                ContentGrpcMapper.map(internalMessageProto.getContent()),
                internalMessageProto.getTimestamp());
    }

    public static InternalMessageProto unmap(InternalMessage internalMessage) {
        return InternalMessageProto.newBuilder()
                .setInternalMessageId(InternalMessageIdGrpcMapper.unmap(internalMessage.getInternalMessageId()))
                .setUserFrom(UserGrpcMapper.unmap(internalMessage.getUserFrom()))
                .setUserTo(UserGrpcMapper.unmap(internalMessage.getUserTo()))
                .setItem(ItemGrpcMapper.unmap(internalMessage.getItem()))
                .setContent(ContentGrpcMapper.unmap(internalMessage.getContent()))
                .setTimestamp(internalMessage.getTimestamp())
                .build();
    }
}
