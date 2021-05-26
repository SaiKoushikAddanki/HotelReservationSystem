package com.koushik.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.koushik.apigateway.exception.ServiceBusinessException;
import com.koushik.apigateway.service.GatewayService;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilterConfig> {

	Logger log = LoggerFactory.getLogger(AuthFilter.class);
	@Autowired
	private RouterValidator routerValidator;
	@Autowired
	private GatewayService service;
	
	@Override
	public GatewayFilter apply(AuthFilterConfig config) {

		return (exchange, chain) -> {
			final ServerHttpRequest request = exchange.getRequest();
			ServerHttpRequest modifiedRequest = request;
			if (routerValidator.isSecured.test(request)) {
				final boolean authorization = request.getHeaders().containsKey("Authorization");

				if (!authorization)
					throw new ServiceBusinessException("Authorization key is missing");
				log.info("Authorization key is there ");
				final String token = request.getHeaders().get("Authorization").get(0).substring(7);
				if (token.isEmpty() || !service.validateToken(token))
					throw new ServiceBusinessException("Invalid Token KEY");
				log.info("token is validated ");
				try {
					modifiedRequest = exchange.getRequest().mutate().header("Authorization", token).build();
				} catch (Exception e) {
					throw new ServiceBusinessException("Modified Request ");
				}
			}
			return chain.filter(exchange.mutate().request(modifiedRequest).build());

		};
	}
}
