package com.example.ecomerce.api_gateway.config;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }
    @Bean
    public Capability capability(MeterRegistry meterRegistry) {
        return new MicrometerCapability(meterRegistry);
    }
}
