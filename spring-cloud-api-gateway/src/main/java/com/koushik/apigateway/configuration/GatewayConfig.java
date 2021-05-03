package com.koushik.apigateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koushik.apigateway.filters.AuthFilter;
import com.koushik.apigateway.filters.AuthFilterConfig;

@Configuration
public class GatewayConfig{
	@Autowired
	private AuthFilter authenticationFilter;

	@Bean
	public RouteLocator gateWayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("guest-service", r -> r.path("/guestservice/**")
				.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig()))).uri("http://localhost:8092/"))
				.route("hotel-service",
						r -> r.path("/hotel/**")
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri("http://localhost:8091/"))
				.route("reservation-service",
						r -> r.path("/reserve/**")
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri("http://localhost:8093/"))
				.route("auth-service-jwt",
						r -> r.path("/**")
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri("http://localhost:9001/"))
				.build();

	}
	
	@Bean
	public HttpMessageConverters getHttpMessageConverters() {
		return new HttpMessageConverters();
	}
}
