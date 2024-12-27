package com.week1.IntroSpringBoot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component
public class Apple {

    @PostConstruct
    void callThisMethodBeforeUse(){
        System.out.println("creating apple bean , before use");
    }

    public void eatApple() {
        System.out.println("iam eating apple");
    }

    @PreDestroy
    void callThisMethodBeforeDestroy(){
        System.out.println("Destroying apple bean....");
    }
}
