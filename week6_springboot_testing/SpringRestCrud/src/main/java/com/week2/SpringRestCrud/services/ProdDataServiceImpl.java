package com.week2.SpringRestCrud.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdDataServiceImpl implements DataService{
    @Override
    public String getData() {
        return "prod data";
    }
}
