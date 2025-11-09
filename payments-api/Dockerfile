FROM openjdk:17-ea-3-jdk-slim
COPY target/payments-api-1.0-SNAPSHOT.jar payments-api.jar
EXPOSE 8080
CMD ["java","-jar", "payments-api.jar"]
