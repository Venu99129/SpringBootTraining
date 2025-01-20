package com.example.corrency_converter.client;

import com.example.corrency_converter.advice.ApiResponse;
import com.example.corrency_converter.entities.CurrencyData;

public interface CurrencyClient {

    ApiResponse<CurrencyData> getAllCurrency(String fromCurrency ,String toCurrency ,Double units);
}
