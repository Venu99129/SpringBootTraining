package com.example.spring_security_assignment.utils;

import com.example.spring_security_assignment.exceptions.IntegerFormatException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;

public class IntegerValidatorDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            String value = jsonParser.getText().trim();
            if (value.contains(".")) {
                throw new IntegerFormatException("Given " + value + " is not a valid integer. Only integer values are accepted.");
            }

            // If it's not a decimal, try parsing it as an integer
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IntegerFormatException("Given " + jsonParser.getText().trim() + " number format is wrong, accept Integer only");
        }
    }
}
