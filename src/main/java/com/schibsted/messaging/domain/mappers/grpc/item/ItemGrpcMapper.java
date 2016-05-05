package com.schibsted.messaging.domain.mappers.grpc.item;

import com.schibsted.messaging.domain.models.item.Item;
import com.schibsted.messaging.server.grpc.ItemProto;

public class ItemGrpcMapper {

    public static Item map(ItemProto itemProto) {
        return new Item(
                itemProto.getType(),
                itemProto.getId());
    }

    public static ItemProto unmap(Item item) {
        return ItemProto.newBuilder()
                .setType(item.getType())
                .setId(item.getId())
                .build();
    }
}
