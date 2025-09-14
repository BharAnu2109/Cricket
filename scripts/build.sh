#!/bin/bash

echo "Building Cricket Management System..."

# Build the entire project
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    
    # Build Docker images
    echo "🐳 Building Docker images..."
    
    docker build -t cricket/discovery-service:latest ./discovery-service
    docker build -t cricket/api-gateway:latest ./api-gateway  
    docker build -t cricket/player-service:latest ./player-service
    
    echo "✅ Docker images built successfully!"
    echo "📋 Available images:"
    docker images | grep cricket
    
else
    echo "❌ Build failed!"
    exit 1
fi