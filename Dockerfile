# Stage 1: Build Stage
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download project dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run Stage
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/ask-url-shortner-1.0.0.jar /app/ask-url-shortner-1.0.0.jar

# Expose the application port
EXPOSE 8083

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/ask-url-shortner-1.0.0.jar"]
