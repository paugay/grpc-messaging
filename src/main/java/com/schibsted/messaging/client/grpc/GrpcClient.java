package com.schibsted.messaging.client.grpc;

import com.schibsted.messaging.domain.mappers.grpc.InternalMessageGrpcMapper;
import com.schibsted.messaging.domain.models.Content;
import com.schibsted.messaging.domain.models.InternalMessage;
import com.schibsted.messaging.domain.models.InternalMessageId;
import com.schibsted.messaging.domain.models.item.Item;
import com.schibsted.messaging.domain.models.user.User;
import com.schibsted.messaging.domain.models.user.UserId;
import com.schibsted.messaging.server.grpc.Empty;
import com.schibsted.messaging.server.grpc.InternalMessageProto;
import com.schibsted.messaging.server.grpc.MessageRepositoryProtoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpcClient {
    private final static Logger logger = Logger.getLogger(GrpcClient.class.getName());

    private final ManagedChannel channel;
    private final MessageRepositoryProtoGrpc.MessageRepositoryProtoBlockingStub blockingStub;

    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("localhost", 50051);

        try {
            client.simulate();
        } finally {
            client.shutdown();
        }
    }

    public GrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

        blockingStub = MessageRepositoryProtoGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void simulate() {
        InternalMessage internalMessage = createInternalMessage();

        save(internalMessage);

        printAll();
    }

    public InternalMessage createInternalMessage() {
        User userFrom = User.builder()
                .userId(UserId.builder().id("1000").build())
                .name("Pau Gay")
                .email("pau.gay@schibsted.com")
                .build();

        User userTo = User.builder()
                .userId(UserId.builder().id("2000").build())
                .name("Jon Smith")
                .email("jon@smith.com")
                .build();

        return InternalMessage.builder()
                .internalMessageId(InternalMessageId.builder().id(UUID.randomUUID().toString()).build())
                .userFrom(userFrom)
                .userTo(userTo)
                .item(Item.builder().type("ad").id("1234").build())
                .content(Content.builder().title("Subject").body("Hey man!").build())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public void save(InternalMessage internalMessage) {
        try {
            InternalMessageProto internalMessageProto = blockingStub.createMessage(InternalMessageGrpcMapper.unmap(internalMessage));

            logger.info("Stored message with id: " + internalMessageProto.getInternalMessageId());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
        }
    }

    public void printAll() {
        try {
            Iterator<InternalMessageProto> internalMessageProtoIterator = blockingStub.getMessageList(
                    Empty.getDefaultInstance());

            logger.info("Printing all messages");

            InternalMessage internalMessage;
            while (internalMessageProtoIterator.hasNext()) {
                internalMessage = InternalMessageGrpcMapper.map(internalMessageProtoIterator.next());
                logger.info(internalMessage.toString());
            }
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
        }
    }
}
