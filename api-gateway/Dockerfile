FROM openjdk:17-jre-slim

LABEL maintainer="Cricket Management System Team"
LABEL service="api-gateway"

WORKDIR /app

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create non-root user
RUN groupadd -r cricket && useradd -r -g cricket cricket

# Copy the jar file
COPY target/api-gateway-*.jar app.jar

# Change ownership
RUN chown cricket:cricket app.jar

# Switch to non-root user
USER cricket

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]