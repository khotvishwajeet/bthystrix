package com.bt.springcloudgatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(p -> p
                        .path("/myfirst/message")
                        .filters(f ->
                                f.hystrix(config -> config.setName("first-service")
                                                .setFallbackUri("forward:/firstservicefallback"))
                        )
                        .uri("http://localhost:8084/myfirst/message")
                )
                .route(p -> p
                        .path("/mySecond/message")
                        .filters(f ->
                                f.hystrix(config -> config.setName("second-service")
                                                .setFallbackUri("forward:/secondservicefallback"))
                        )
                        .uri("http://localhost:8085/mySecond/message")
                )
                .build();
    }
}
