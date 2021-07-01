/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import za.gov.sars.common.DataSourceConfiguration;

/**
 *
 * @author S2028389
 */
public class WebAppInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
               // Create the root and the dispatcher servlet's Spring application context
        //we initialize it
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext(); //does not use xml we will be using annotation
        //registers the data source that we will be using
        ctx.register(DataSourceConfiguration.class);
        // we gonna be using our own servlet
        ctx.setServletContext(servletContext);
        //Have listeners that will be listening called context laoder which loads app well
        servletContext.addListener(new ContextLoaderListener(ctx));
        //when you click a button that listener will work
        servletContext.addListener(new RequestContextListener());
        //to set servlet
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/");//
        dynamic.setLoadOnStartup(1);//you are setting a priority of the page it starts



    }
    
}
