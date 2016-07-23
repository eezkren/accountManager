package com.isilona.accm.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.isilona.accm.config.web.WebConfig;
import com.isilona.accm.config.web.WebSecurityConfig;

/**
 * 
 * {@link WebAppInitializer} is centralized access point for presentation-tier request handling,
 * designed to be a central Servlet that dispatches requests to controllers, content retrieval, view
 * management, and navigation. This Servlet handles mapped requests by using URL mappings
 * 
 * {@link WebAppInitializer} is created for the needs of Web component only.
 * 
 * @author iivanov
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private static final String WEB_CTX_PATH = "/web/*";
    private static final String WEB_SERVLET_NAME = "web-dispatcher";
    private static final int WEB_LOAD_ON_STARTUP_PRIORITY = 1;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
     * .ServletContext)
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class, WebSecurityConfig.class);

        // Manage the life cycle of the root application context
        // Application Context is the container initialized by a ContextLoaderListener
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Set the "web" dispatcher servlet
        setWebDispatcherServlet(servletContext);

        // FILTERS

        // The OpenEntityManagerInViewFilter prevents the JPA transactional session from being
        // closed, as a result of the request/response cycle
        registerOpenEntityManagerInViewFilter(servletContext);

    }

    /**
     * Create, register and map the "web" dispatcher servlet.
     */

    private void setWebDispatcherServlet(ServletContext container) {
        // Create the "web" dispatcher servlet
        AnnotationConfigWebApplicationContext webDispatcherServlet = new AnnotationConfigWebApplicationContext();
        webDispatcherServlet.register(WebConfig.class);

        // Register and map the "web" dispatcher servlet
        ServletRegistration.Dynamic webDispatcher = container.addServlet(WEB_SERVLET_NAME, new DispatcherServlet(
                webDispatcherServlet));
        webDispatcher.setLoadOnStartup(WEB_LOAD_ON_STARTUP_PRIORITY);
        webDispatcher.addMapping(WEB_CTX_PATH);
    }

    private void registerOpenEntityManagerInViewFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration = servletContext.addFilter("openEntityManagerInView",
                new OpenEntityManagerInViewFilter());
        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false,
                WEB_CTX_PATH);
    }

}
