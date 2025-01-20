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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyClientImpl implements CurrencyClient {

    private final RestClient restClient;

    private Logger log = LoggerFactory.getLogger(CurrencyClientImpl.class);

    @Override
    public ApiResponse<CurrencyData> getAllCurrency(String fromCurrency, String toCurrency, Double units) {
        log.trace("calling currency api to with our custom api");
        log.info(" this is getAllCurrency method.......");

        ApiResponse<CurrencyData> currencyDataApiResponse = restClient.get()
                .uri("&&currencies="+fromCurrency+"&&base_currency="+toCurrency)
                .retrieve()
                .body(new ParameterizedTypeReference<ApiResponse<CurrencyData>>() {
                });
        log.debug("successfully we fetched data from currency convert api");
        log.trace("retrieved all currency daya from currency api :{}",currencyDataApiResponse.getData());

        Map<String , Double> actualData = currencyDataApiResponse.getData().getCurrencyValues();

        actualData.replaceAll((key,value)-> value*units);

        currencyDataApiResponse.getData().setCurrencyValues(actualData);

        log.trace("updated values with INR : {}" , currencyDataApiResponse.getData());

        return currencyDataApiResponse;
    }
}
