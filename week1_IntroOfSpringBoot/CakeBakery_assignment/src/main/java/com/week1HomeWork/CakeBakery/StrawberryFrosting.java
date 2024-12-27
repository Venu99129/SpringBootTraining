package com.week1HomeWork.CakeBakery;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.flavor", havingValue = "strawberry")
public class StrawberryFrosting implements Frosting {
    @Override
    public String getFrosting() {
        return "Strawberry frosting";
    }
}
