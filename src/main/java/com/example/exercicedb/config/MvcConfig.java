package com.example.exercicedb.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.example.exercicedb.*","com.example.exercicedb.middleware.*" })
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = { "com.example.exercicedb.*" })
public class MvcConfig implements WebMvcConfigurer {


}
