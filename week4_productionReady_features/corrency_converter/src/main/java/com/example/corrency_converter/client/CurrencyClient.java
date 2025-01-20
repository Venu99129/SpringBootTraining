package com.example.corrency_converter.client;

import com.example.corrency_converter.advice.ApiResponse;
import com.example.corrency_converter.entities.CurrencyData;

import java.util.List;

public interface CurrencyClient {

    ApiResponse<CurrencyData> getAllCurrency(String fromCurrency , List<String> toCurrency , Double units);
}
