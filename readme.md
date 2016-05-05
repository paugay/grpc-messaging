Messaging gRPC sample
====

Messaging gRPC sample is a experimental project that tries to compare performance between different RPC methods. Initially, the idea is to compare gRPC, Thrift and plain HTTP. 

It's based in a domain layer, the specific RPC layer and a simple repository where the domain objects are stored.

Overview
---

DOMAIN --> map(domainObject) : rpcObject --> RPC LAYER --> unmap(rpcObject) : domainObject --> REPOSITORY LAYER

Setup
---

1. Clone the repository
2. Import into Eclipse / IntelliJ as a Maven project
3. Run the maven target `generate-sources` from the command line, that will automate generate **\target\generated-sources\protobuf\grpc** and **\target\generated-sources\protobuf\java** packages, which includes the protobuf objects and grpc stubs.

Example:

```
mvn clean generate-sources
```


gRPC example:
---

From the IDE, run `GrpcServer`, and then `GrpcClient`. This will execute a message exchange between client and server. Shall be readable enough to go through the source code.


REST API example:
---

To start the HTTP Server, run the `HttpServer` from the IDE. This will start a Spring Boot applicatin with an embedded web server with a Jersey REST API.

To test the api, you can do the following calls via command line (client comming soon):

#### POST /api/messages

```
curl -v -X POST -d '{"internalMessageId":{"id":"123"},"userFrom":{"userId":{"id":"asd"},"name":"name","email":"email"},"userTo":{"userId":{"id":"asd"},"name":"name","email":"email"},"item":{"type":"type","id":"id"},"content":{"title":"title","body":"body"},"timestamp":1461483618397}' -H "Content-Type: application/json" localhost:8080/api/messages```
```

#### GET /api/messages

```
curl -v localhost:8080/api/messages
```

#### GET /api/messages/{messageId}

```
curl -v localhost:8080/api/messages/123
```
