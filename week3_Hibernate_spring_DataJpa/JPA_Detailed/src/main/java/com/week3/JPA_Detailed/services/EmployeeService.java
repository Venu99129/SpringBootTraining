package com.week3.JPA_Detailed.services;

import com.week3.JPA_Detailed.entities.EmployeeEntity;
import com.week3.JPA_Detailed.repositorys.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository EmployeeRepository;

    public EmployeeService(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    public List<EmployeeEntity> getAllEmployees(){
        return EmployeeRepository.findAll();
    }

    public EmployeeEntity createNewEmployees(EmployeeEntity employee){
        return EmployeeRepository.save(employee);
    }

    public EmployeeEntity getByEmployeeId(Long employeeId){
        return EmployeeRepository.findById(employeeId).orElse(null);
    }
}
