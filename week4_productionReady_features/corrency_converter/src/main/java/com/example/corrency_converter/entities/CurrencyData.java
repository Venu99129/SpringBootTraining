package com.example.corrency_converter.entities;

import org.springframework.stereotype.Component;

import java.util.Map;

@lombok.Data
@Component
public class CurrencyData {
    private Map<String , Double> currencyValues;
}
