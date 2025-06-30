FROM openjdk:22-jdk-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/personal-financial-dashboard-0.0.1-SNAPSHOT.jar"]