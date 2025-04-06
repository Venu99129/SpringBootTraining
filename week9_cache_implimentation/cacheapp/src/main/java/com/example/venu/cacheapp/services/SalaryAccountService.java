package com.example.venu.cacheapp.services;

import com.example.venu.cacheapp.entities.EmployeeEntity;
import com.example.venu.cacheapp.entities.SalaryAccount;
import com.example.venu.cacheapp.repositorys.EmployeeRepository;
import com.example.venu.cacheapp.repositorys.SalaryAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaryAccountService {

    private final EmployeeRepository employeeRepository;
    private final SalaryAccountRepository salaryAccountRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE , propagation = Propagation.MANDATORY)
    public SalaryAccount createAccount(EmployeeEntity employee) {
        SalaryAccount account = new SalaryAccount();
        account.setBalance(0.0);
        account.setEmployee(employee);
        return salaryAccountRepository.save(account);
    }

    public Optional<SalaryAccount> getAccountById(Long id) {
        return salaryAccountRepository.findById(id);
    }

    public SalaryAccount getByEmployeeId(Long employeeId) {
        return salaryAccountRepository.findByEmployeeId(employeeId).orElseThrow(()-> new RuntimeException("account not found with employeeID: "+employeeId));
    }

    public SalaryAccount updateBalance(Long accountId, double newBalance) {
        Optional<SalaryAccount> optional = salaryAccountRepository.findById(accountId);
        if (optional.isPresent()) {
            SalaryAccount account = optional.get();
            account.setBalance(newBalance);
            return salaryAccountRepository.save(account);
        }
        throw new RuntimeException("Account not found");
    }

    @Transactional
    public SalaryAccount updateBalance1(Long id) {
        SalaryAccount account = salaryAccountRepository.findByEmployeeId(id)
                .orElseThrow(()-> new RuntimeException("employee not found with id "+id));

        account.setBalance(account.getBalance()+1L);

        return salaryAccountRepository.save(account);
    }
}
