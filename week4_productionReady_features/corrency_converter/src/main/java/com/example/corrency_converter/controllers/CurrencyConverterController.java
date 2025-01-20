package com.example.corrency_converter.controllers;

import com.example.corrency_converter.advice.ApiResponse;
import com.example.corrency_converter.client.CurrencyClient;
import com.example.corrency_converter.entities.CurrencyData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyConverterController {

    private final CurrencyClient currencyClient;

    CurrencyConverterController(CurrencyClient currencyClient){
        this.currencyClient=currencyClient;
    }

    @GetMapping(path = "/convertCurrency")
    public ApiResponse<CurrencyData> getAllCurrencies(@RequestParam String fromCurrency, @RequestParam String toCurrency , @RequestParam Double units){
        return currencyClient.getAllCurrency(fromCurrency , toCurrency , units);
    }

}
