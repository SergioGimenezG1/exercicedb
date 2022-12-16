package com.example.exercicedb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Autowired
    private ObjectMapper objectMapper;

    public ApplicationConfig() {
    }

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        // Converters
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper);
        formHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        converters.add(jsonConverter);
        converters.add(formHttpMessageConverter);

        // Do any additional configuration here
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }

}