# Cricket Management System

A comprehensive Cricket Management System built with Spring Boot microservices architecture, featuring modern technologies including Docker, Kubernetes, and cloud-ready configurations.

## Architecture Overview

This system follows a microservices architecture pattern with the following components:

### Core Services
- **Discovery Service (Eureka Server)**: Service registry and discovery
- **API Gateway**: Entry point for all client requests with circuit breaker pattern
- **Player Service**: Manages cricket player information and statistics
- **Team Service**: Manages team information and player assignments *(Coming Soon)*
- **Match Service**: Manages cricket matches and scoring *(Coming Soon)*
- **Statistics Service**: Aggregates and provides statistical data *(Coming Soon)*
- **Auth Service**: Authentication and authorization *(Coming Soon)*

### Technology Stack
- **Framework**: Spring Boot 3.2.0
- **Cloud**: Spring Cloud 2023.0.0
- **Database**: PostgreSQL (Production), H2 (Development)
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Circuit Breaker**: Resilience4j
- **Documentation**: OpenAPI 3.0 (Swagger)
- **Containerization**: Docker
- **Orchestration**: Kubernetes
- **Build Tool**: Maven

## Features

### Design Patterns Implemented
- **Repository Pattern**: Data access abstraction
- **Service Layer Pattern**: Business logic separation
- **Factory Pattern**: Object creation (ApiResponse factory methods)
- **Circuit Breaker Pattern**: Fault tolerance
- **Gateway Pattern**: API routing and composition

### Key Features
- RESTful APIs with comprehensive validation
- Pagination and filtering support
- Global exception handling
- Health checks and monitoring
- Circuit breaker for fault tolerance
- Docker containerization
- Kubernetes deployment manifests
- Multi-environment configuration
- API documentation with Swagger UI

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker & Docker Compose
- Kubernetes cluster (optional)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/BharAnu2109/Cricket.git
   cd Cricket
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

4. **Access the services**
   - API Gateway: http://localhost:8080
   - Discovery Service: http://localhost:8761
   - Player Service: http://localhost:8081
   - Player API Documentation: http://localhost:8081/swagger-ui.html

### Manual Service Startup

1. **Start Discovery Service**
   ```bash
   cd discovery-service
   mvn spring-boot:run
   ```

2. **Start API Gateway**
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

3. **Start Player Service**
   ```bash
   cd player-service
   mvn spring-boot:run
   ```

## API Documentation

### Player Service Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/players` | Create a new player |
| GET | `/api/players/{id}` | Get player by ID |
| GET | `/api/players` | Get all players (with pagination) |
| PUT | `/api/players/{id}` | Update player |
| DELETE | `/api/players/{id}` | Delete player |
| GET | `/api/players/country/{country}` | Get players by country |
| GET | `/api/players/role/{role}` | Get players by role |
| GET | `/api/players/search?name={name}` | Search players by name |
| PUT | `/api/players/{id}/activate` | Activate player |
| PUT | `/api/players/{id}/deactivate` | Deactivate player |

### Sample Request (Create Player)
```json
{
  "firstName": "Virat",
  "lastName": "Kohli",
  "dateOfBirth": "1988-11-05",
  "country": "India",
  "playingRole": "BATSMAN",
  "battingStyle": "RIGHT_HANDED",
  "bowlingStyle": "RIGHT_ARM_MEDIUM",
  "jerseyNumber": 18,
  "isActive": true
}
```

## Docker Deployment

### Build Docker Images
```bash
# Build all services
docker build -t cricket/discovery-service ./discovery-service
docker build -t cricket/api-gateway ./api-gateway
docker build -t cricket/player-service ./player-service
```

### Run with Docker Compose
```bash
docker-compose up -d
```

## Kubernetes Deployment

### Deploy to Kubernetes
```bash
# Create namespace
kubectl apply -f k8s/namespace.yaml

# Deploy configurations
kubectl apply -f k8s/configmaps/
kubectl apply -f k8s/secrets/

# Deploy services
kubectl apply -f k8s/deployments/
kubectl apply -f k8s/services/
kubectl apply -f k8s/ingress/
```

### Access via Ingress
Add to your `/etc/hosts`:
```
127.0.0.1 cricket.local
```

Access: http://cricket.local

## Configuration

### Environment Profiles
- **default**: Development with H2 database
- **docker**: Docker environment with PostgreSQL
- **k8s**: Kubernetes environment with PostgreSQL

### Database Configuration
- **Development**: H2 in-memory database
- **Production**: PostgreSQL with connection pooling

## Monitoring and Health Checks

All services expose actuator endpoints:
- Health: `/actuator/health`
- Info: `/actuator/info`
- Metrics: `/actuator/metrics`

## Testing

### Run Tests
```bash
mvn test
```

### Test Coverage
The project includes:
- Unit tests for services
- Integration tests for repositories
- API endpoint tests

## Project Structure

```
cricket-management-system/
├── common/                    # Shared utilities and models
├── discovery-service/         # Eureka server
├── api-gateway/              # Spring Cloud Gateway
├── player-service/           # Player management
├── team-service/             # Team management (Coming Soon)
├── match-service/            # Match management (Coming Soon)
├── statistics-service/       # Statistics aggregation (Coming Soon)
├── auth-service/             # Authentication (Coming Soon)
├── k8s/                      # Kubernetes manifests
├── docker-compose.yml        # Docker Compose configuration
└── README.md
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For questions and support, please create an issue in the GitHub repository.