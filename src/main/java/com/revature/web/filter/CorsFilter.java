package com.revature.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("CORS Filter leveraged");
		
		if(!(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
			return;
		}
		
		// Cast the response as an HttpServletResponse

		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader("Access-Control-Allow-Origin", "null"); // Allow all origins
		
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		// Allow specific HTTP Verbs
		
		res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type,"
				+ "Access-Control-Request-Method, Access-Control-Request-Headers");
		// Allow specific HTTP Headers (there's a fair few)
		
		res.setHeader("Access-Control-Allow-Credentials", "true");
		// Credentials are allowed
		
		chain.doFilter(request, response);
		// Continue the filter chain
	}

}
