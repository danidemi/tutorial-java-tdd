package com.danidemi.tutorial.tdd.web.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializerListener implements ServletContextListener {

	private static final String INITIALIZER = "initializer";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute(INITIALIZER, new MemoryInitializer());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {		
	}
	
	static public Initializer getInitializer(ServletContext ctx) {
		return ctx != null ? (Initializer) ctx.getAttribute(INITIALIZER) : null;
	}

}
