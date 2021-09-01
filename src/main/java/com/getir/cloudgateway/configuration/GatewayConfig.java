package com.getir.cloudgateway.configuration;

import com.getir.cloudgateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/api/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("auth-swagger", r -> r.path("/auth/v3/api-docs").uri("lb://auth"))
                .route("order", r -> r.path("/api/order/**").filters(f -> f.filter(filter)).uri("lb://order"))
                .route("order-swagger", r -> r.path("/order/v3/api-docs").uri("lb://order"))
                .route("book", r -> r.path("/api/book/**").filters(f -> f.filter(filter)).uri("lb://book"))
                .route("book-swagger", r -> r.path("/book/v3/api-docs").uri("lb://book"))
                .route("customer", r -> r.path("/api/customer/**").filters(f -> f.filter(filter)).uri("lb://customer"))
                .route("customer-swagger", r -> r.path("/customer/v3/api-docs").uri("lb://customer"))
                .route("statistics", r -> r.path("/api/statistics/**").filters(f -> f.filter(filter)).uri("lb://statistics"))
                .route("statistics-swagger", r -> r.path("/statistics/v3/api-docs").uri("lb://statistics"))
                .build();
    }

}