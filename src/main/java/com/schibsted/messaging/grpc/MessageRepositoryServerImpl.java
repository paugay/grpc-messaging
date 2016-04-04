package com.schibsted.messaging.grpc;

import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class MessageRepositoryServerImpl implements MessageRepositoryGrpc.MessageRepository {

    @Override
    public void get(InternalMessageId request, StreamObserver<InternalMessage> responseObserver) {
        // we return a fake one
        responseObserver.onNext(createInternalMessage(request));
        responseObserver.onCompleted();
    }

    @Override
    public void save(InternalMessage request, StreamObserver<InternalMessageId> responseObserver) {
        // pretend we save it
        responseObserver.onNext(request.getId());
        responseObserver.onCompleted();
    }

    public InternalMessage createInternalMessage(InternalMessageId internalMessageId) {
        User userFrom = User.newBuilder()
                .setId(UserId.newBuilder().setId(UUID.randomUUID().toString()).build())
                .setName("Pau")
                .setEmail("pau@email.com")
                .build();

        User userTo = User.newBuilder()
                .setId(UserId.newBuilder().setId(UUID.randomUUID().toString()).build())
                .setName("Grbriel")
                .setEmail("gabriel@email.com")
                .build();

        Item item = Item.newBuilder()
                .setType("ad")
                .setId("1234")
                .build();

        Content content = Content.newBuilder()
                .setTitle("Message title")
                .setBody("Message body")
                .build();

        return InternalMessage.newBuilder()
                .setId(internalMessageId)
                .setUserFrom(userFrom)
                .setUserTo(userTo)
                .setItem(item)
                .setContent(content)
                .build();
    }
}