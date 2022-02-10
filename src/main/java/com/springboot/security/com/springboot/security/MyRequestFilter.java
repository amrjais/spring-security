package com.springboot.security.com.springboot.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.security.com.springboot.security.jwt.JWTUtil;
import com.springboot.security.com.springboot.security.service.MyUserDetailService;

@Component
public class MyRequestFilter extends OncePerRequestFilter {
	@Autowired
	MyUserDetailService detailService;
	@Autowired
	JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		String user = null;
		String token = null;

		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			user = jwtUtil.extractUsername(token);
		}
		if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.detailService.loadUserByUsername(user);
			if (jwtUtil.vaidatetoken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
