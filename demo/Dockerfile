# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper executable and POM file
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the entire project and build it
COPY src src

# Run the Maven build (this will also download dependencies)
RUN ./mvnw package

# Copy the built JAR file to the working directory
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]
