package com.example.venu.cacheapp.repositorys;


import com.example.venu.cacheapp.entities.SalaryAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalaryAccount> findByEmployeeId(Long employeeId);
}
