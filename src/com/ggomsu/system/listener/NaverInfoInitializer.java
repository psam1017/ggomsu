package com.ggomsu.system.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ggomsu.system.naver.NaverInfo;

@WebListener
public class NaverInfoInitializer implements ServletContextListener {

    public NaverInfoInitializer() { ; }
    public void contextDestroyed(ServletContextEvent arg0)  { ; }

    public void contextInitialized(ServletContextEvent sce)  {
    	String clientId = sce.getServletContext().getInitParameter("naverClientId");
    	String clientSecret = sce.getServletContext().getInitParameter("naverClientSecret");
    	
    	new NaverInfo(clientId, clientSecret);
    }
}
