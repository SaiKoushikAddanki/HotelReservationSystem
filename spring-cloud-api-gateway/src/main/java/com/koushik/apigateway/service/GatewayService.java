package com.koushik.apigateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.koushik.apigateway.configuration.IFeignClientConfig;
import com.koushik.apigateway.models.AuthenticationRequest;
import com.koushik.apigateway.models.AuthenticationResponse;
import com.koushik.apigateway.response.ApiResponse;

@Service
public class GatewayService {
	
	Logger log = LoggerFactory.getLogger(GatewayService.class);

	@Autowired
	@Lazy
	private IFeignClientConfig client;
	
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) {
		return client.generateTokenFromAuth(authenticationRequest);
	}
	
	public boolean validateToken(String token) {
		log.info("START:: validate token method in Gateway service");
		ApiResponse<Boolean> result = client.validateTokenProvided(token);
		return result.getData();
		
	}
}
