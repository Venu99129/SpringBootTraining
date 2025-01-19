package com.week4.productReadyFeatures.clients.impl;

import com.week4.productReadyFeatures.advice.ApiResponse;
import com.week4.productReadyFeatures.clients.EmployeeClient;
import com.week4.productReadyFeatures.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    private Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("trying retrieve all employees from getAllEmployees method");
        log.info("get all employees method");
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {}",employeeDTOList.getData());
            return employeeDTOList.getData();
        }catch (Exception e){
            log.error("error occurred in getAllEmployees",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("trying retrieve employee from getEmployeeById method id : {}",employeeId);
        log.info("getEmployeeById method");
        try{
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDTO>>() {
                    });
            log.debug("successfully retrieved the employee in getEmployeeByID");
            log.trace("Retrieved employees in getEmployeeById : {}",employeeDTOApiResponse.getData());
            return employeeDTOApiResponse.getData();
        }catch (Exception e){
            log.error("error occurred in getEmployeeById",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("trying to create employee in createNewEmployee method with data :{}",employeeDTO);
        log.info("createNewEmployee method");
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , (req , res)->{
                        log.error("4xxx error occurred in createNewEmployee method.");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new RuntimeException("i am unable to create new employee......");
                    })
                    .toEntity(new ParameterizedTypeReference<ApiResponse<EmployeeDTO>>() {
                    });
            log.debug("successfully created the employee by createNewEmployee method");
            log.trace("created employee in createNewEmployee : {}",employeeDTOApiResponse.getBody().getData());
            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e){
            log.error("error occurred in createNewEmployee",e);
            throw new RuntimeException(e);
        }
    }
}
