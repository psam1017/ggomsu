package com.ggomsu.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private String encoding = null;
	
    public EncodingFilter() { ; }
    public void destroy() { ; }

    public void init(FilterConfig fConfig) throws ServletException {
    	this.encoding = fConfig.getInitParameter("encoding");
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(request.getCharacterEncoding() == null || !request.getCharacterEncoding().equals(this.encoding)) {
			request.setCharacterEncoding(encoding);
		}
		if(request.getCharacterEncoding() == null || !response.getCharacterEncoding().equals(this.encoding)) {
			response.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}
}
