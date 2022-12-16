package com.example.demo;

import com.example.exercicedb.DemoApplication;
import com.example.exercicedb.domain.request.RateRequest;
import com.example.exercicedb.middleware.data.CurrencyDto;
import com.example.exercicedb.controller.ConstantsPaths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ContextConfiguration(initializers = RateControllerIT.DataSourceInitializer.class)
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8090)
public class RateControllerIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ObjectMapper objectMapper;

    public static class DataSourceInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.jdbc-url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            );
        }
    }

    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @Test
    public void createRate() throws Exception {

        CurrencyDto currencyData = CurrencyDto.builder()
                .code("EUR")
                .decimals(2)
                .symbol("€")
                .build();

        // given - precondition or setup
        RateRequest rate = RateRequest.builder()
                .brandId(1)
                .productId(1)
                .startDate(LocalDate.of(2023, 1, 1))
                .endDate(LocalDate.of(2023, 2, 1))
                .currencyCode("EUR")
                .price(1000)
                .build();

        stubFor(
                WireMock.get(WireMock.urlPathMatching("/v1/currencies/.*"))
                        .willReturn(ok()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", "application/json")
                                .withBody(objectMapper.writeValueAsString(currencyData)))
        );

        // when - action or behaviour that we are going test
        this.webTestClient
                .post()
                .uri(ConstantsPaths.Paths.Rate.RATE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(rate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("price").isEqualTo("10,00 €")
                .jsonPath("currency").isEqualTo("EUR");

    }


}
