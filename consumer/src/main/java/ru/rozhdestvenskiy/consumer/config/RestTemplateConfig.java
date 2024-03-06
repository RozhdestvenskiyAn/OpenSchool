package ru.rozhdestvenskiy.consumer.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.rozhdestvenskiy.consumer.integration.supplier.handler.SupplierExceptionHandler;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getSupplierRestTemplate(){
        RestTemplate suplierRestTemplate = new RestTemplate();
        suplierRestTemplate.setErrorHandler(new SupplierExceptionHandler());
        return suplierRestTemplate;
    }
}
