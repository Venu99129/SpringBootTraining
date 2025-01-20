package com.example.corrency_converter.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@lombok.Data
@Component
public class CurrencyData {

    @JsonProperty("data")
    private Map<String , Double> currencyValues;
}
