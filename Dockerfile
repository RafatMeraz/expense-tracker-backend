# -------- STAGE 1: Build the JAR --------
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application (skip tests if needed)
RUN mvn clean package -DskipTests

# -------- STAGE 2: Run with Corretto 21 --------
FROM amazoncorretto:21

# Set working directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose app port
EXPOSE 8080

# JVM options for production
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-XX:+UseZGC", "-jar", "app.jar"]
