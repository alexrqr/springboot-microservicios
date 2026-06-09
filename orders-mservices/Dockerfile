FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY pom.xml ./
COPY orders-mservices orders-mservices
WORKDIR /workspace/orders-mservices
RUN chmod +x mvnw && ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/orders-mservices/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
