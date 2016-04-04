package com.schibsted.messaging.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageRepositoryClient {
    private static final Logger logger = Logger.getLogger(MessageRepositoryClient.class.getName());

    private final ManagedChannel channel;
    private final MessageRepositoryGrpc.MessageRepositoryBlockingStub blockingStub;

    public MessageRepositoryClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

        blockingStub = MessageRepositoryGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void saveMessage() {
        try {
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

            InternalMessage internalMessage = InternalMessage.newBuilder()
                    .setId(InternalMessageId.newBuilder().setId(UUID.randomUUID().toString()).build())
                    .setUserFrom(userFrom)
                    .setUserTo(userTo)
                    .setItem(item)
                    .setContent(content)
                    .build();

            InternalMessageId internalMessageId = blockingStub.save(internalMessage);
            logger.info("Stored message with id: " + internalMessageId.getId());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        MessageRepositoryClient client = new MessageRepositoryClient("localhost", 50051);

        try {
            client.saveMessage();
        } finally {
            client.shutdown();
        }
    }
}
