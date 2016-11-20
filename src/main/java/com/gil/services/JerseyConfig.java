package com.gil.services;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/service")
public class JerseyConfig extends ResourceConfig {

    private static String[] SUPPORTED_PACKAGES = {"com.gil.services"}; //The package containing relevant service classes

    JerseyConfig() {
        //register all services from the supported packages defined above
        packages(SUPPORTED_PACKAGES);
        register(SUPPORTED_PACKAGES);
    }
}
