#!/bin/bash

echo "Deploying Cricket Management System to Kubernetes..."

# Apply namespace
kubectl apply -f k8s/namespace.yaml

# Apply configurations
kubectl apply -f k8s/configmaps/
kubectl apply -f k8s/secrets/

# Apply deployments
kubectl apply -f k8s/deployments/

# Apply services
kubectl apply -f k8s/services/

# Apply ingress
kubectl apply -f k8s/ingress/

echo "✅ Deployment complete!"
echo "📋 Checking pod status:"
kubectl get pods -n cricket-system

echo "📋 Checking service status:"
kubectl get services -n cricket-system

echo "🌐 Access the application:"
echo "  - API Gateway: http://cricket.local"
echo "  - Discovery Service: http://cricket.local/eureka"