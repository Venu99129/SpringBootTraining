package com.week2.SpringRestCrud.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDataServiceImpl implements DataService{
    @Override
    public String getData() {
        return "dev data";
    }
}
