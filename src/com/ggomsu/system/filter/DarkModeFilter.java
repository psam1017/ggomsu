package com.ggomsu.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DarkModeFilter implements Filter {

	
    public DarkModeFilter() { ; }
    public void destroy() { ; }

    public void init(FilterConfig fConfig) throws ServletException { ; }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("darkModeFlag")) {
					if(c.getValue().equals("on")) {
						session.setAttribute("darkModeFlag", true);
					}
					else {
						session.setAttribute("darkModeFlag", false);
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}
}
