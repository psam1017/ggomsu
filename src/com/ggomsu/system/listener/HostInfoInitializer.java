package com.ggomsu.system.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ggomsu.system.mail.HostInfo;

@WebListener
public class HostInfoInitializer implements ServletContextListener {

    public HostInfoInitializer() { ; }
    public void contextDestroyed(ServletContextEvent arg0)  { ; }

    public void contextInitialized(ServletContextEvent sce)  {
    	String host = sce.getServletContext().getInitParameter("mailHost");
    	String email = sce.getServletContext().getInitParameter("email");
    	String password = sce.getServletContext().getInitParameter("mailPassword");
    	
    	new HostInfo(host, email, password);
    }
}
