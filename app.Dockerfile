FROM --platform=amd64 openjdk:8-jdk as build

# Set the working directory to /app
WORKDIR /app

# Copy the Gradle wrapper files
COPY gradle/wrapper/gradle-wrapper.jar .
COPY gradle/wrapper/gradle-wrapper.properties .

COPY gradlew gradlew
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts

# Copy the Kotlin source code
COPY src /app/src

RUN chmod +x gradlew

# Copy the Gradle build files
COPY gradle /app/gradle
# Build the application
RUN ./gradlew bootJarProd

FROM --platform=amd64 openjdk:8-jdk as runtime

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file
COPY --from=build /app/build .

# Expose port 8080 for the application
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "libs/loan-0.0.3-SNAPSHOT.jar"]