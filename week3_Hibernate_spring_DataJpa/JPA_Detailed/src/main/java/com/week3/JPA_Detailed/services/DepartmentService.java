package com.week3.JPA_Detailed.services;

import com.week3.JPA_Detailed.entities.DepartmentEntity;
import com.week3.JPA_Detailed.entities.EmployeeEntity;
import com.week3.JPA_Detailed.repositorys.DepartmentRepository;
import com.week3.JPA_Detailed.repositorys.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    public List<DepartmentEntity> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public DepartmentEntity createNewDepartments(DepartmentEntity department){
        return departmentRepository.save(department);
    }

    public DepartmentEntity getByDepartmentId(Long departmentId){
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long managerId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(managerId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManger(Long employeeId) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
//        return employeeEntity.map(EmployeeEntity::getManagedDepartment).orElse(null);

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();
        return departmentRepository.findByManager(employeeEntity);
    }

    @Transactional
    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);
                    System.out.println(employee.toString());
                    System.out.println(department.getWorkers().isEmpty());
                     department.getWorkers().add(employee);
                    System.out.println(department.toString());
                     return department;
                })).orElse(null);
    }

    public DepartmentEntity deleteWorkerFromDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.setWorkerDepartment(null);
                    employeeRepository.save(employee);
                    department.getWorkers().remove(employee);
                    return department;
                })).orElse(null);
    }

    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.getFreelancerDepartments().add(department);
                    employeeRepository.save(employee);
                    department.getFreelancers().add(employee);
                    return department;
                })).orElse(null);
    }
}
