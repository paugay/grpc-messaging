package com.schibsted.messaging.server.grpc;

import com.schibsted.messaging.domain.services.HashMapMessageRepository;
import com.schibsted.messaging.domain.services.MessageRepository;
import io.grpc.ServerBuilder;

import java.util.logging.Logger;

public class GrpcServer {
    private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());

    private final MessageRepository messageRepository;

    private int port = 50051;
    private io.grpc.Server server;

    public GrpcServer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(MessageRepositoryProtoGrpc.bindService(new GrpcServerImpl(messageRepository)))
                .build()
                .start();

        logger.info("GrpcServer started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // use stderr here since the logger may have been reset by its JVM shutdown hook
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        final MessageRepository messageRepository = new HashMapMessageRepository();
        final GrpcServer server = new GrpcServer(messageRepository);

        server.start();
        server.blockUntilShutdown();
    }
}
