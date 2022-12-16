package com.example.exercicedb.middleware.service.impl;


import com.example.exercicedb.controller.ConstantsPaths;
import com.example.exercicedb.middleware.data.CurrencyDto;
import com.example.exercicedb.middleware.exception.MiddlewareException;
import com.example.exercicedb.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResourcePathProperties resourcePathProperties;

    @Override
    public CurrencyDto getCurrencyByCode(String currencyCode) {
        log.info("Init for getCurrencyByCode {}", currencyCode);
        Assert.notNull(currencyCode, "currencyCode");

        try {
            return restTemplate.exchange(resourcePathProperties.getCurrencyServer() + ConstantsPaths.Paths.Currency.CURRENCY_BY_CODE,
                    HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), CurrencyDto.class, currencyCode).getBody();
        } catch (Exception exception) {
            throw new MiddlewareException(String.format("Failed to get the currencyCode %s", currencyCode), exception);
        }

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
