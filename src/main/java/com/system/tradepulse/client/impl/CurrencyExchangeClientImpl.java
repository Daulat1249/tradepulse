package com.system.tradepulse.client.impl;

import com.system.tradepulse.dto.CurrencyExchangeDTO;
import com.system.tradepulse.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> 03654b2127463b0a4222963f1ab84a2576bd81e8
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Locale;

<<<<<<< HEAD
@Slf4j
=======
>>>>>>> 03654b2127463b0a4222963f1ab84a2576bd81e8
@Service
@RequiredArgsConstructor
public class CurrencyExchangeClientImpl {

    private final RestClient restClient;

    //uri = https://api.frankfurter.app/latest?from=INR&to=USD

    public CurrencyExchangeDTO getCurrentExchange(String targetCurrency){
        String targetCurrency1 = targetCurrency.toUpperCase();
<<<<<<< HEAD
        log.debug("Building a RestClient request for targetCurrency");
=======
>>>>>>> 03654b2127463b0a4222963f1ab84a2576bd81e8
        CurrencyExchangeDTO response = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/latest")
                        .queryParam("from","INR")
                        .queryParam("to", targetCurrency1).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response1) -> {
<<<<<<< HEAD
                    log.error("Currencies are not supported");
                    throw new ResourceNotFoundException("Invalid currency");
                }))
                .onStatus(HttpStatusCode::is5xxServerError, ((request, response1) -> {
                    log.error("Server is Not Responding");
=======
                    throw new ResourceNotFoundException("Invalid currency");
                }))
                .onStatus(HttpStatusCode::is5xxServerError, ((request, response1) -> {
>>>>>>> 03654b2127463b0a4222963f1ab84a2576bd81e8
                    throw new RuntimeException("External Server Error");
                }))
                .body(CurrencyExchangeDTO.class);
        return response;
    }
}
