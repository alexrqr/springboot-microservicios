FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY pom.xml ./
COPY api-gateway api-gateway
WORKDIR /workspace/api-gateway
RUN chmod +x mvnw && ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/api-gateway/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
