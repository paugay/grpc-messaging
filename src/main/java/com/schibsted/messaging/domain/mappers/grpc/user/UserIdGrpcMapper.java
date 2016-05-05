package com.schibsted.messaging.domain.mappers.grpc.user;

import com.schibsted.messaging.domain.models.user.UserId;
import com.schibsted.messaging.server.grpc.UserIdProto;

public class UserIdGrpcMapper {

    public static UserId map(UserIdProto userIdProto) {
        return new UserId(userIdProto.getId());
    }

    public static UserIdProto unmap(UserId userId) {
        return UserIdProto.newBuilder()
                .setId(userId.getId())
                .build();
    }
}
