# Use Java base image
FROM eclipse-temurin:17-jdk-jammy

# Copy jar file
COPY target/student-auth-1.0.jar app.jar

# Run app
ENTRYPOINT ["java","-jar","/app.jar"]
