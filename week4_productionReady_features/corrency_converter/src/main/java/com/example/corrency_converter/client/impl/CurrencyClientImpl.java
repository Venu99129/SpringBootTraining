package com.example.corrency_converter.client.impl;

import com.example.corrency_converter.advice.ApiResponse;
import com.example.corrency_converter.client.CurrencyClient;
import com.example.corrency_converter.entities.CurrencyData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyClientImpl implements CurrencyClient {

    private final RestClient restClient;

    private final Logger log = LoggerFactory.getLogger(CurrencyClientImpl.class);

    private final List<String> currencies = List.of("USD" , "EUR" , "CAD");

    @Override
    public ApiResponse<CurrencyData> getAllCurrency(String fromCurrency, List<String> toCurrency, Double units) {
        log.trace("calling currency api to with our custom api with following inputs {} {} {}", fromCurrency, toCurrency, units);
        log.info(" this is getAllCurrency method.......");

        for (String currency : toCurrency) {
            if(!currencies.contains(currency)){
                log.error("raising RunTimeException with msg : toCurrency must should be a USD,EUR,CND  in correct format...");
                RuntimeException exception = new RuntimeException("toCurrency must should be a USD,EUR,CND  in correct format...");
                log.debug("throwing RunTime exception : ", exception);
                throw exception;
            }
        }

        StringBuilder toCurrencyBuilder = new StringBuilder();
        for(int i = 0; i< toCurrency.size(); i++){
            if(i==0) toCurrencyBuilder = new StringBuilder(toCurrency.get(i));
            else toCurrencyBuilder.append("%2C").append(toCurrency.get(i));
        }

        String finalToCurrency = toCurrencyBuilder.toString();
        CurrencyData currencyData = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("currencies", finalToCurrency)
                        .queryParam("base_currency",fromCurrency)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<CurrencyData>() {
                });
        log.debug("successfully we fetched data from currency convert api");
        log.trace("retrieved all currency daya from currency api :{}",currencyData.getCurrencyValues());
        ApiResponse<CurrencyData> currencyDataApiResponse = new ApiResponse<>(currencyData);
        Map<String , Double> actualData = currencyDataApiResponse.getData().getCurrencyValues();
        actualData.replaceAll((key,value)-> value*units);

        currencyDataApiResponse.getData().setCurrencyValues(actualData);

        log.trace("updated values with INR : {}" , currencyDataApiResponse.getData());

        return currencyDataApiResponse;
    }
}