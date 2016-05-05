package com.schibsted.messaging.server.grpc;

import com.schibsted.messaging.domain.mappers.grpc.InternalMessageGrpcMapper;
import com.schibsted.messaging.domain.mappers.grpc.InternalMessageIdGrpcMapper;
import com.schibsted.messaging.domain.models.InternalMessage;
import com.schibsted.messaging.domain.models.InternalMessageId;
import com.schibsted.messaging.domain.services.MessageRepository;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class GrpcServerImpl implements MessageRepositoryProtoGrpc.MessageRepositoryProto {
    private final MessageRepository messageRepository;

    public GrpcServerImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void getMessageList(Empty request, StreamObserver<InternalMessageProto> responseObserver) {
        // use repository
        List<InternalMessage> internalMessageList = messageRepository.getMessageList();

        for (InternalMessage internalMessage : internalMessageList) {
            // unmap + add to observer
            responseObserver.onNext(InternalMessageGrpcMapper.unmap(internalMessage));
        }

        // return
        responseObserver.onCompleted();
    }

    @Override
    public void getMessage(InternalMessageIdProto request, StreamObserver<InternalMessageProto> responseObserver) {
        // map
        InternalMessageId internalMessageId = InternalMessageIdGrpcMapper.map(request);

        // use repository
        InternalMessage internalMessage = messageRepository.getMessage(internalMessageId);

        // unmap
        InternalMessageProto internalMessageProto = InternalMessageGrpcMapper.unmap(internalMessage);

        // return
        responseObserver.onNext(internalMessageProto);
        responseObserver.onCompleted();
    }

    @Override
    public void createMessage(InternalMessageProto request, StreamObserver<InternalMessageProto> responseObserver) {
        // map
        InternalMessage internalMessage = InternalMessageGrpcMapper.map(request);

        // use repository
        InternalMessage returnInternalMessage = messageRepository.createMessage(internalMessage);

        // unmap
        InternalMessageProto internalMessageProto = InternalMessageGrpcMapper.unmap(returnInternalMessage);

        // return
        responseObserver.onNext(internalMessageProto);
        responseObserver.onCompleted();

    }

    @Override
    public void updateMessage(InternalMessageProto request, StreamObserver<InternalMessageProto> responseObserver) {
        // map
        InternalMessage internalMessage = InternalMessageGrpcMapper.map(request);

        // use repository
        InternalMessage returnInternalMessage = messageRepository.updateMessage(internalMessage);

        // unmap
        InternalMessageProto internalMessageProto = InternalMessageGrpcMapper.unmap(returnInternalMessage);

        // return
        responseObserver.onNext(internalMessageProto);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteMessage(InternalMessageIdProto request, StreamObserver<InternalMessageProto> responseObserver) {
        // map
        InternalMessageId internalMessageId = InternalMessageIdGrpcMapper.map(request);

        // use repository
        InternalMessage internalMessage = messageRepository.deleteMessage(internalMessageId);

        // unmap
        InternalMessageProto internalMessageProto = InternalMessageGrpcMapper.unmap(internalMessage);

        // return
        responseObserver.onNext(internalMessageProto);
        responseObserver.onCompleted();

    }
}