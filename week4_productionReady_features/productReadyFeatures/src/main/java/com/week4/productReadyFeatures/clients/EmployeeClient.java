package com.week4.productReadyFeatures.clients;


import com.week4.productReadyFeatures.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
