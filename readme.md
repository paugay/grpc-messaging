Messaging gRPC sample
====

1. Import into Eclipse / IntelliJ as a maven project
2. Run the maven target `generate-sources`, that will automate generate **\target\generated-sources\protobuf\grpc** and **\target\generated-sources\protobuf\java** package which includes protobuf objects and grpc stubs
3. From the IDE, run `MessageRepositoryServer`, and then `MessageRepositoryClient` -- this will execute a message exchange between client and server
