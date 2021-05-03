package com.koushik.apigateway.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.koushik.apigateway.response.ApiResponse;

@FeignClient(url = "http://localhost:9001", name = "GATEWAY-CLIENT")
public interface IFeignClientConfig {

	
	@GetMapping("/validate/{token}")
	public ApiResponse<Boolean> validateTokenProvided(@PathVariable(value= "token") String token);

}
