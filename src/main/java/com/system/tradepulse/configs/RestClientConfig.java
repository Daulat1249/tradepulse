package com.system.tradepulse.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient getCurrencyExchangeClient(){
        String BASE_URL = "https://api.frankfurter.dev/v1";
<<<<<<< HEAD
=======
        //"[https://api.frankfurter.app](https://api.frankfurter.app)";
>>>>>>> 03654b2127463b0a4222963f1ab84a2576bd81e8
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
