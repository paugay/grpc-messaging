package com.schibsted.messaging.grpc;

import io.grpc.ServerBuilder;

import java.util.logging.Logger;

public class MessageRepositoryServer {
    private static final Logger logger = Logger.getLogger(MessageRepositoryServer.class.getName());

    private int port = 50051;
    private io.grpc.Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(MessageRepositoryGrpc.bindService(new MessageRepositoryServerImpl()))
                .build()
                .start();

        logger.info("MessageRepositoryServer started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // use stderr here since the logger may have been reset by its JVM shutdown hook
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                MessageRepositoryServer.this.stop();
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
        final MessageRepositoryServer server = new MessageRepositoryServer();
        server.start();
        server.blockUntilShutdown();
    }
}
