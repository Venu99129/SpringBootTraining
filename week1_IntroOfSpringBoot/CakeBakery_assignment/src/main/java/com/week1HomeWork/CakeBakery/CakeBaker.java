package com.week1HomeWork.CakeBakery;

import org.springframework.stereotype.Service;

@Service
public class CakeBaker {

    Frosting frosting;
    Syrup syrup;

    CakeBaker(Frosting frosting, Syrup syrup){
        this.frosting= frosting;
        this.syrup = syrup;
    }

    public void bakeCake(){
        System.out.println("baking cake with "+frosting.getFrosting()+"  "+syrup.getSyrup());
    }
}
