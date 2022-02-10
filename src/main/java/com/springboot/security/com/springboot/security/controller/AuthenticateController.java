package com.springboot.security.com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.com.springboot.security.jwt.JWTUtil;
import com.springboot.security.com.springboot.security.oespecific.IHelloWorldService;
import com.springboot.security.com.springboot.security.oespecific.MatcherClass;
import com.springboot.security.com.springboot.security.pojo.AuthenticateRequest;
import com.springboot.security.com.springboot.security.pojo.AuthenticateResponse;
import com.springboot.security.com.springboot.security.service.MyUserDetailService;

/**
 * @author amrjais
 *
 */
@RestController
public class AuthenticateController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailService detailService;

	@Autowired
	JWTUtil jwtUtil;

	@Autowired(required = false)
	IHelloWorldService helloWorldService;

	@RequestMapping("/hello")
	public String getHelloWorld() {
		if (helloWorldService != null) {
			return helloWorldService.getHelloWorld(  );
		}

		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticateRequest.getUserName(), authenticateRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("incorrect credentials", e);
		}

		UserDetails username = detailService.loadUserByUsername(authenticateRequest.getUserName());

		String generateToken = jwtUtil.generateToken(username);

		return ResponseEntity.ok(new AuthenticateResponse(generateToken));

	}
}
