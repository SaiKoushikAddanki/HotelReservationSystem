package com.koushik.authservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.authservice.models.AuthenticationRequest;
import com.koushik.authservice.models.AuthenticationResponse;
import com.koushik.authservice.response.ApiResponse;
import com.koushik.authservice.service.MyuserDetailsService;
import com.koushik.authservice.utilities.JwtUtil;

@RestController
public class AuthController {
	
	Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	MyuserDetailsService myuserDetailsService;
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid username or password", e);
		}

		final UserDetails userDetails = myuserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/welcome")
	public String getInitialMessage() {
		return "Welcome to the Hotel Reservation System";
	}

	@GetMapping("/validate/{token}")
	public ApiResponse<Boolean> validateToken(@PathVariable String token) {
		log.info("START:: Validate token method");
		ApiResponse<Boolean> response = new ApiResponse<>();
		response.setStatus(HttpStatus.OK);
		response.setMessage("An exception occured in the system and got Handled");
		response.setData(jwtUtil.validateToken(token, myuserDetailsService.loadUserByUsername(jwtUtil.extractUsername(token))));
		return response;
	}
}
