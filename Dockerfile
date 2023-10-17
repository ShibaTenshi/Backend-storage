FROM openjdk:17-jdk-slim
COPY target/Backend-storage-0.0.1.jar storage.jar
EXPOSE 5034
ENTRYPOINT ["java","-jar","./storage.jar"]