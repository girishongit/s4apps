package com.cg.pbs.customerprofile;
/**
 * 
 * @author gbangalo
 * #description: This is an alternative way to enable cors, and filter all the incoming request.
 * 					There are actually 3 ways to enable cors in Spring Boot, with priority as per sequence below
 * 						1. @CrossOrigin(origins = {"https://resttesttest.com", "https://extendsclass.com"}) / @CrossOrigin(origins = "https://resttesttest.com")
 * 								This has a highest priority and can be operated at method level  or at controller level, method level has highest prio than controller level, and it can be applied at both the level
 *						2. Global Configuration of CorsConfiguration, i.e. extending or implementing WebMvcConfigurer, and this is an recommended way when enabled with spring security
 *						3. Enabling Cors by implementing Filters such as the current implementation
 *
 */

/*
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter implements Filter {

@Override
public void init(FilterConfig filterConfig) {
}

@Override
public void destroy() {
}

@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

	 HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;

	    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, x-xhr-logon");

	    chain.doFilter(req, res);
	
}

}*/

public class CustomCorsFilter{
	
}