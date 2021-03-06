package com.azrin.auth.controller;

import com.azrin.auth.dto.JwtRequest;
import com.azrin.auth.service.CustomUserDetailsService;
import com.azrin.auth.utils.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(ApiVersion.API_VERSION)
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService jwtInMemoryUserDetailsService;

	@PostMapping(value = AllEndPoints.LOGIN_POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<JSONObject> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)throws Exception {

		logger.info("login start: userName: "+authenticationRequest.getUsername()+" password: "+authenticationRequest.getPassword());
		logger.info("performing authentication");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.info("token: "+token);
		JSONObject responseBody = new JSONObject();
		responseBody.put(Constant.RESPONSE_BODY_DATA, token);
		responseBody.put(Constant.RESPONSE_BODY_STATUS, HttpStatus.OK);
		responseBody.put(Constant.RESPONSE_BODY_MESSAGE, Constant.MESSAGE_SUCCESSFUL);

		logger.info(responseBody.toString());
		return ResponseEntity.ok(responseBody);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.info("Exception: "+e.getMessage());
			throw new Exception(ExceptionMessage.USER_DISABLE, e);
		} catch (BadCredentialsException e) {
			logger.info("Exception: "+e.getMessage());
			throw new Exception(ExceptionMessage.INVALID_CREDENTIALS, e);
		}
	}
}
