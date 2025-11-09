FROM openjdk:17-ea-3-jdk-slim
COPY target/auth-server-1.0-SNAPSHOT.jar auth-server.jar
EXPOSE 9090
CMD ["java" ,"-jar", "auth-server.jar"]