package com.schibsted.messaging.domain.mappers.grpc.user;

import com.schibsted.messaging.domain.models.user.User;
import com.schibsted.messaging.server.grpc.UserProto;

public class UserGrpcMapper {

    public static User map(UserProto userProto) {
        return new User(
                UserIdGrpcMapper.map(userProto.getUserId()),
                userProto.getName(),
                userProto.getEmail());
    }

    public static UserProto unmap(User user) {
        return UserProto.newBuilder()
                .setUserId(UserIdGrpcMapper.unmap(user.getUserId()))
                .setName(user.getName())
                .setEmail(user.getEmail())
                .build();
    }
}
