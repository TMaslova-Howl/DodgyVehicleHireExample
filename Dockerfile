# Start with a base image containing Java runtime
FROM openjdk:15-jdk-alpine

# The application's .jar file
ARG JAR_FILE=target/*.jar

# Add the application's .jar to the container
ADD ${JAR_FILE} app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]