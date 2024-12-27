package com.week1HomeWork.CakeBakery;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.flavor", havingValue = "strawberry")
public class StrawberrySyrup implements Syrup {
    @Override
    public String getSyrup() {
        return "Strawberry syrup";
    }
}
