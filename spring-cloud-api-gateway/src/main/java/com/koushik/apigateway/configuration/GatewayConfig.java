package com.koushik.apigateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${guest.service.id}")
	private String guestId;
	@Value("${hotel.service.id}")
	private String hotelId;
	@Value("${reservation.service.id}")
	private String reservationId;
	@Value("${auth.service.id}")
	private String authId;
	@Value("${guest.service.uri}")
	private String guestURI;
	@Value("${hotel.service.uri}")
	private String hotelURI;
	@Value("${reservation.service.uri}")
	private String reservationURI;
	@Value("${auth.service.uri}")
	private String authURI;
	@Value("${guest.service.path}")
	private String guestPath;
	@Value("${hotel.service.path}")
	private String hotelPath;
	@Value("${reservation.service.path}")
	private String reservationPath;
	@Value("${auth.service.path}")
	private String authPath;

	@Bean
	public RouteLocator gateWayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route(guestId, r -> r.path(guestPath)
				.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig()))).uri(guestURI))
				.route(hotelId,
						r -> r.path(hotelPath)
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri(hotelURI))
				.route("reservation-service",
						r -> r.path(reservationPath)
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri(reservationURI))
				.route("auth-service-jwt",
						r -> r.path(authPath)
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri(authURI))
				.build();

	}
	
	@Bean
	public HttpMessageConverters getHttpMessageConverters() {
		return new HttpMessageConverters();
	}
}
