FROM maven:3.9.0-eclipse-temurin-8 AS build

WORKDIR /build
COPY pom.xml ./
RUN mvn --batch-mode dependency:go-offline

COPY src ./src
RUN mvn --batch-mode clean package -DskipTests

FROM eclipse-temurin:8-jre-noble

WORKDIR /app
COPY --from=build /build/target/smart-expense-tracker-api-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
