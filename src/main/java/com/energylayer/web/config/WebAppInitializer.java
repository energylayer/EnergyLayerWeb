package com.energylayer.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author rkotelnikov
 */

public class WebAppInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext
                = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        rootContext.register(SecurityConfig.class);
        rootContext.refresh();

        servletContext.addListener(new ContextLoaderListener(rootContext));

        servletContext.setInitParameter("defaultHtmlEscape", "true");

        FilterRegistration.Dynamic filterRegistrator
                = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filterRegistrator.setInitParameter("encoding", "UTF-8");
        filterRegistrator.setInitParameter("forceEncoding", "true");
        filterRegistrator.addMappingForUrlPatterns(null, true, "/*");


        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebConfig.class);

        ServletRegistration.Dynamic registration =
                servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));

        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
