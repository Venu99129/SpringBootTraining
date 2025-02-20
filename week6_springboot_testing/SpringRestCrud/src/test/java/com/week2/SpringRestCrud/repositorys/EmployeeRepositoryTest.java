package com.week2.SpringRestCrud.repositorys;

import com.week2.SpringRestCrud.entities.EmployeeEntity;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employee;

    @BeforeEach
    void setup(){
        employee = EmployeeEntity.builder()
                .Id(1L)
                .age(25)
                .name("venu")
                .email("venumadhav@gmail.com")
                .salary(23.989)
                .role("Employee")
                .dateOfJoining(LocalDate.now())
                .isActive(true)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsValid_thenReturnEmployee(){
        //Arrange Given
        employeeRepository.save(employee);

        //Act, When
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByEmail(employee.getEmail());


        //Assert, Then
        assertThat(employeeEntityList).isNotNull().isNotEmpty();
        assertThat(employeeEntityList).hasSize(1);
        assertThat(employeeEntityList.getFirst().getEmail()).isEqualTo(employee.getEmail());

    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList(){
        //Given
        String email = "iuqelmw.@jncd.com";

        //When
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByEmail(email);

        //Then
        assertThat(employeeEntityList).isEmpty();
        //assertThat(employeeEntityList).isNotEmpty();
    }

}