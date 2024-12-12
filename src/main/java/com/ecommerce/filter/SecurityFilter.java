package com.ecommerce.filter;

import org.springframework.stereotype.Component;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.util.JwtUtils;
@Component
public class SecurityFilter extends OncePerRequestFilter{ 
	@Autowired
	private JwtUtils jwtUtils ;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=request.getParameter("Authorization");
		System.err.println(token);
		if(token!=null)
		{
			String username=jwtUtils.getUsername(token);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UserDetails user=userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken AuthenticationToken =new UsernamePasswordAuthenticationToken(username,user.getPassword(),user.getAuthorities());
				AuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

		
}
