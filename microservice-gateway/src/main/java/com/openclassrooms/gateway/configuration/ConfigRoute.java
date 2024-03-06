package com.openclassrooms.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRoute {

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route("r_path_patient", r -> r.path("/Patient*/**", "/Patients")
                                                .uri("http://localhost:8082"))
                                .route("r_ui", r -> r
                                                .path("/Accueil", "/AjouterPatient", "/AddPatient", "/MAJPatient/**",
                                                                "/UpdatePatient/**")
                                                .uri("http://localhost:9090"))
                                .build();
        }

}
