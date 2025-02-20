package com.example.spring_security_assignment.services;


import com.example.spring_security_assignment.dto.EmployeeDto;
import com.example.spring_security_assignment.entities.EmployeeEntity;
import com.example.spring_security_assignment.exceptions.ResourceNotFoundException;
import com.example.spring_security_assignment.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository , ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    public List<EmployeeDto> findAllEmployees(){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class)).toList();
    }

    public Optional<EmployeeDto> findById(Long id){
        isExistsByEmployeeId(id);

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class));
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        EmployeeEntity mappedEmployeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(mappedEmployeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    public void isExistsByEmployeeId(Long id){
       boolean exists = employeeRepository.existsById(id);
       if(!exists) throw new ResourceNotFoundException("no such employee fount with id :"+id);
    }

    public Boolean deleteEmployee(Long id){
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        EmployeeEntity mappedEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        mappedEntity.setId(employeeId);
        EmployeeEntity updatedEmployee = employeeRepository.save(mappedEntity);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    public EmployeeDto updatePartialEmployee(Long employeeId, Map<String, Object> updates) {
       isExistsByEmployeeId(employeeId);
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class , field);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated,employee,value);
        });
        return modelMapper.map(employeeRepository.save(employee) , EmployeeDto.class);
    }
}
