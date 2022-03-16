FROM adoptopenjdk/openjdk11:alpine-slim
ADD target/spring-webflux-customer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]