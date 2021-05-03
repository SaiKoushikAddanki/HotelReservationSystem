package com.koushik.apigateway.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.koushik.apigateway.models.AuthenticationRequest;
import com.koushik.apigateway.models.AuthenticationResponse;
import com.koushik.apigateway.response.ApiResponse;

@FeignClient(url = "http://localhost:9001", name = "GATEWAY-CLIENT")
public interface IFeignClientConfig {

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> generateTokenFromAuth(@RequestBody AuthenticationRequest authenticationRequest);
	
	@GetMapping("/validate/{token}")
	public ApiResponse<Boolean> validateTokenProvided(@PathVariable(value= "token") String token);

}
