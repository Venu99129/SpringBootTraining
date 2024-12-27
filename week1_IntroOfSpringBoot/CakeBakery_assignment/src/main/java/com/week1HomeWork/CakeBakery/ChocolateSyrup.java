package com.week1HomeWork.CakeBakery;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.flavor", havingValue = "chocolate")
public class ChocolateSyrup implements Syrup{
    @Override
    public String getSyrup() {
        return "chocolate syrup";
    }
}
