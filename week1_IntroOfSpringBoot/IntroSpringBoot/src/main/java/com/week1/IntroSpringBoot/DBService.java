package com.week1.IntroSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

//    @Autowired
    final DB db;

    public DBService(DB db){
        this.db = db;
    }

    void getDBData(){
        System.out.println(db.getData());
    }
}
