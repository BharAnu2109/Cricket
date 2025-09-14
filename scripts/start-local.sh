#!/bin/bash

echo "Starting Cricket Management System locally..."

# Check if Docker is running
if ! docker info >/dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Start services with Docker Compose
docker-compose up --build -d

if [ $? -eq 0 ]; then
    echo "✅ Services started successfully!"
    echo "📋 Service status:"
    docker-compose ps
    
    echo "🌐 Access the services:"
    echo "  - API Gateway: http://localhost:8080"
    echo "  - Discovery Service: http://localhost:8761"
    echo "  - Player Service: http://localhost:8081"
    echo "  - Player API Docs: http://localhost:8081/swagger-ui.html"
    
    echo "📊 Logs:"
    echo "  - View logs: docker-compose logs -f [service-name]"
    echo "  - Stop services: docker-compose down"
    
else
    echo "❌ Failed to start services!"
    exit 1
fi