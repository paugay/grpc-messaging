Messaging gRPC sample
====

1. Import into Eclipse / IntelliJ as a Maven project
2. Run the maven target `generate-sources` from the IDE, that will automate generate **\target\generated-sources\protobuf\grpc** and **\target\generated-sources\protobuf\java** package which includes protobuf objects and grpc stubs

```
mvn clean generate-sources
```

3. From the IDE, run `MessageRepositoryServer`, and then `MessageRepositoryClient` -- this will execute a message exchange between client and server

For the rest:

POST /api/messages
```
curl -v -X POST -d '{"internalMessageId":{"id":"123"},"userFrom":{"userId":{"id":"asd"},"name":"name","email":"email"},"userTo":{"userId":{"id":"asd"},"name":"name","email":"email"},"item":{"type":"type","id":"id"},"content":{"title":"title","body":"body"},"timestamp":1461483618397}' -H "Content-Type: application/json" localhost:8080/api/messages```
```

GET /api/messages
```
curl -v localhost:8080/api/messages
```

GET /api/messages/{messageId}
```
curl -v localhost:8080/api/messages/123
```
