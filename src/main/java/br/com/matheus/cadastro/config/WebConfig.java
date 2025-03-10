package br.com.matheus.cadastro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // Via QUERRY PARAM. http://localhost:8080/api/person/v1?mediaType=xml

        /*
         * configurer.favorParameter(true) .parameterName("mediaType")
         * .ignoreAcceptHeader(true) .useRegisteredExtensionsOnly(false)
         * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
         * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
         */

        // Via HEADER PARAM. http://localhost:8080/api/person/v1

        configurer.favorParameter(false).ignoreAcceptHeader(false).useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON).mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML).mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrings = corsOriginPatterns.split(",");
        registry.addMapping("/**").allowedMethods("*").allowedOrigins(allowedOrings).allowCredentials(true);
    }
}
