package com.example.exercicedb.middleware.service.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "resource.path")
public class ResourcePathProperties {
    private String currencyServer;

}
