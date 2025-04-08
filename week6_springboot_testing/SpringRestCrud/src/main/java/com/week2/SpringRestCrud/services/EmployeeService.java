package com.week2.SpringRestCrud.services;

import com.week2.SpringRestCrud.Exceptions.ResourceNotFoundException;
import com.week2.SpringRestCrud.dto.EmployeeDto;
import com.week2.SpringRestCrud.entities.EmployeeEntity;
import com.week2.SpringRestCrud.repositorys.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
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
        isExistingByEmail(mappedEmployeeEntity.getEmail());
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(mappedEmployeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    public void isExistsByEmployeeId(Long id){
       boolean exists = employeeRepository.existsById(id);
       if(!exists) throw new ResourceNotFoundException("no such employee fount with id :"+id);
    }

    public void isExistingByEmail(String email){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByEmail(email);
        if(!employeeEntityList.isEmpty()){
            throw new RuntimeException("employee with this email: "+email+" all ready existed");
        }
    }

    public Boolean deleteEmployee(Long id){
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity oldEmployee = employeeRepository.findById(employeeId).get();
        if(!oldEmployee.getEmail().equals(employeeDto.getEmail()))
            throw new RuntimeException("user not allow to update email : "+oldEmployee.getEmail()+" to "+employeeDto.getEmail());
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
           Class<?> fieldType = fieldToUpdated.getType();
           if (fieldType == LocalDate.class) {
               value = LocalDate.parse((String) value); // Convert string to LocalDate
           } else if (fieldType == Integer.class && value instanceof Number) {
               value = ((Number) value).intValue(); // Handle Integer conversion
           } else if (fieldType == Double.class && value instanceof Number) {
               value = ((Number) value).doubleValue(); // Handle Double conversion
           } else if (fieldType == Long.class && value instanceof Number) {
               value = ((Number) value).longValue(); // Handle long conversion
           }

           fieldToUpdated.setAccessible(true);
           ReflectionUtils.setField(fieldToUpdated,employee,value);
       });
       return modelMapper.map(employeeRepository.save(employee) , EmployeeDto.class);
    }
}
