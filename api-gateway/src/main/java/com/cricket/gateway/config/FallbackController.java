package com.cricket.gateway.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Component
public class FallbackController {

    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(GET("/fallback/players"), this::playersFallback)
                .andRoute(GET("/fallback/teams"), this::teamsFallback)
                .andRoute(GET("/fallback/matches"), this::matchesFallback)
                .andRoute(GET("/fallback/statistics"), this::statisticsFallback);
    }

    public Mono<ServerResponse> playersFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\"message\":\"Player service is currently unavailable. Please try again later.\"}"));
    }

    public Mono<ServerResponse> teamsFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\"message\":\"Team service is currently unavailable. Please try again later.\"}"));
    }

    public Mono<ServerResponse> matchesFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\"message\":\"Match service is currently unavailable. Please try again later.\"}"));
    }

    public Mono<ServerResponse> statisticsFallback(ServerRequest request) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\"message\":\"Statistics service is currently unavailable. Please try again later.\"}"));
    }
}