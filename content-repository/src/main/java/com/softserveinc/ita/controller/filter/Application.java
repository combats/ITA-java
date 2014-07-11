package com.softserveinc.ita.controller.filter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.boot.context.embedded.MultipartConfigFactory;
import javax.servlet.MultipartConfigElement;

/**
 * Some Spring-Boot element needed for file-uploading
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    private static final String FILE_SIZE ="28MB"; // was 128KB

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(FILE_SIZE);
        factory.setMaxRequestSize(FILE_SIZE);
        return factory.createMultipartConfig();
    }
}
