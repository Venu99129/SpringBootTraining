package com.example.venu.cacheapp.services;

import com.example.venu.cacheapp.Exceptions.ResourceNotFoundException;
import com.example.venu.cacheapp.dto.EmployeeDto;
import com.example.venu.cacheapp.entities.EmployeeEntity;
import com.example.venu.cacheapp.repositorys.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final String CACHE_NAME = "employees";

    EmployeeService(EmployeeRepository employeeRepository , ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    public List<EmployeeDto> findAllEmployees(){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList.stream().map(emp -> modelMapper.map(emp, EmployeeDto.class)).toList();
    }

    @Cacheable(cacheNames = CACHE_NAME , key = "#id")
    public Optional<EmployeeDto> findById(Long id){
        log.info("fetching employee with id: {}",id);
        isExistsByEmployeeId(id);

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class));
    }

    @CachePut(cacheNames = CACHE_NAME , key = "#result.id")
    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        EmployeeEntity mappedEmployeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
        isExistingByEmail(mappedEmployeeEntity.getEmail());
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(mappedEmployeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }

    public void isExistingByEmail(String email){
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByEmail(email);
        if(!employeeEntityList.isEmpty()){
            throw new RuntimeException("employee with this email: "+email+" all ready existed");
        }
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

    @CachePut(cacheNames = CACHE_NAME , key = "#id")
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        isExistsByEmployeeId(id);
        EmployeeEntity mappedEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        mappedEntity.setId(id);
        EmployeeEntity updatedEmployee = employeeRepository.save(mappedEntity);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    @CachePut(cacheNames = CACHE_NAME , key = "#id")
    public EmployeeDto updatePartialEmployee(Long id, Map<String, Object> updates) {
       isExistsByEmployeeId(id);
        EmployeeEntity employee = employeeRepository.findById(id).get();
        updates.forEach((field,value)->{
            Field fieldToUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class , field);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated,employee,value);
        });
        return modelMapper.map(employeeRepository.save(employee) , EmployeeDto.class);
    }
}
