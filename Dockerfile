# Runtime with Temurin 21
FROM eclipse-temurin:21-jdk-alpine

USER root

ARG APP_NAME="admin-inputs-api"
ENV APP_NAME=${APP_NAME}

WORKDIR /app

# Copy jar from build stage
COPY /app/*.jar ${APP_NAME}.jar

# Expose default Spring Boot port
EXPOSE 8080

COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

# Run the Spring Boot application
ENTRYPOINT ["./entrypoint.sh"]