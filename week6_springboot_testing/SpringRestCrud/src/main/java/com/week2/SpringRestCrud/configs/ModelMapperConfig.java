package com.week2.SpringRestCrud.configs;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping for LocalDate to ensure it works correctly
        modelMapper.addConverter(new Converter<String, LocalDate>() {
            public LocalDate convert(MappingContext<String, LocalDate> context) {
                // Assuming the date is in the format "yyyy-MM-dd"
                return LocalDate.parse(context.getSource());
            }
        });

        return modelMapper;
    }
}
