package com.example.venu.cacheapp.controllers;

import com.example.venu.cacheapp.entities.SalaryAccount;
import com.example.venu.cacheapp.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryAccountService salaryAccountService;

    @PutMapping("/{id}")
    public ResponseEntity<SalaryAccount> updateSalary1(@PathVariable long id){
        return ResponseEntity.ok(salaryAccountService.updateBalance1(id));
    }
}
