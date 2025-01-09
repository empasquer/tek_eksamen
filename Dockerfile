# Use an OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Install Netcat (for waiting for database connection)
RUN apt-get update && apt-get install netcat-traditional

# Copy the Maven wrapper and build files
COPY mvnw ./
COPY .mvn ./.mvn
COPY pom.xml ./

# Make sure mvnw is executable
RUN chmod +x mvnw

# Copy the source code
COPY src ./src

# Build the application using the Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Copy the target folder into the container
COPY target/ ./target/

# Run the Spring Boot app using a wildcard to match the .jar file
CMD ["sh", "-c", "until nc -z database 3306; do echo 'Waiting for database...'; sleep 5; done; java -jar ./target/*.jar"]
