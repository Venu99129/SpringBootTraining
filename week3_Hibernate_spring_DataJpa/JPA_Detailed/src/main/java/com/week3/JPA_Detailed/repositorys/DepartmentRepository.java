package com.week3.JPA_Detailed.repositorys;

import com.week3.JPA_Detailed.entities.DepartmentEntity;
import com.week3.JPA_Detailed.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
