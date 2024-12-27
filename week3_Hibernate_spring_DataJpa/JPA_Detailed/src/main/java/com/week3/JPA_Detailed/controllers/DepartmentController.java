package com.week3.JPA_Detailed.controllers;

import com.week3.JPA_Detailed.entities.DepartmentEntity;
import com.week3.JPA_Detailed.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping()
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity department){
        return departmentService.createNewDepartments(department);
    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getByDepartmentId(departmentId);
    }

    @PutMapping(path = "/{departmentId}/manager/{managerId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId ,
                                                      @PathVariable Long managerId){
        return departmentService.assignManagerToDepartment(departmentId,managerId);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){
        return departmentService.getAssignedDepartmentOfManger(employeeId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId ,
                                                      @PathVariable Long employeeId){
        return departmentService.assignWorkerToDepartment(departmentId,employeeId);
    }

    @DeleteMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity deleteWorkerFromDepartment(@PathVariable Long departmentId ,
                                                     @PathVariable Long employeeId){
        return departmentService.deleteWorkerFromDepartment(departmentId,employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId ,
                                                     @PathVariable Long employeeId){
        return departmentService.assignFreelancerToDepartment(departmentId,employeeId);
    }

}
