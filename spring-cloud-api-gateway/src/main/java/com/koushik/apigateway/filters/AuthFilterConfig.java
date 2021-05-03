package com.koushik.apigateway.filters;

import org.springframework.cloud.gateway.support.Configurable;
import org.springframework.stereotype.Component;
@Component
public class AuthFilterConfig implements Configurable<String> {

	@Override
	public Class<String> getConfigClass() {
		return String.class;
	}

	@Override
	public String newConfig() {
		return "Authorization";
	}

}