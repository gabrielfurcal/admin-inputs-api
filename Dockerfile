# Runtime with Temurin 25
FROM eclipse-temurin:25-jdk-alpine

USER root

ARG APP_NAME="admin-inputs-api"
ENV APP_NAME=${APP_NAME}

WORKDIR /app

# Copy jar from build stage
COPY target/*.jar ${APP_NAME}.jar

# Expose default Spring Boot port
EXPOSE 8080

COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

# Run the Spring Boot application
ENTRYPOINT ["./entrypoint.sh"]