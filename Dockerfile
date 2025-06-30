FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/personal-financial-dashboard-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
