package com.ggomsu.app.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ggomsu.app.mail.domain.HostInfo;

@WebListener
public class HostInfoInitializer implements ServletContextListener {

    public HostInfoInitializer() { ; }
    public void contextDestroyed(ServletContextEvent arg0)  { ; }

    public void contextInitialized(ServletContextEvent sce)  {
    	String host = sce.getServletContext().getInitParameter("host");
    	String email = sce.getServletContext().getInitParameter("email");
    	String password = sce.getServletContext().getInitParameter("password");
    	
    	new HostInfo(host, email, password);
    }
	
}
