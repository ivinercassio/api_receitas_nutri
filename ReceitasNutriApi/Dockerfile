FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY ./ReceitasNutriApi /app
RUN mvn clean install

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
    
