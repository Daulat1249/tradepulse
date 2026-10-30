package com.system.tradepulse.client.impl;

import com.system.tradepulse.dto.CurrencyExchangeDTO;
import com.system.tradepulse.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeClientImpl {

    private final RestClient restClient;

    //uri = https://api.frankfurter.app/latest?from=INR&to=USD

    public CurrencyExchangeDTO getCurrentExchange(String targetCurrency){
        String targetCurrency1 = targetCurrency.toUpperCase();
        CurrencyExchangeDTO response = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/latest")
                        .queryParam("from","INR")
                        .queryParam("to", targetCurrency1).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response1) -> {
                    throw new ResourceNotFoundException("Invalid currency");
                }))
                .onStatus(HttpStatusCode::is5xxServerError, ((request, response1) -> {
                    throw new RuntimeException("External Server Error");
                }))
                .body(CurrencyExchangeDTO.class);
        return response;
    }
}
