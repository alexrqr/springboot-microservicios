FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY pom.xml ./
COPY inventory-mservices inventory-mservices
WORKDIR /workspace/inventory-mservices
RUN chmod +x mvnw && ./mvnw -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/inventory-mservices/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
