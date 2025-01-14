package com.week4.productReadyFeatures;

import com.week4.productReadyFeatures.clients.EmployeeClient;
import com.week4.productReadyFeatures.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(3)
	void getAllEmployeesTest(){
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest(){
		Long employeeId = 2L;
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(employeeId);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createEmployeeTest(){
		EmployeeDTO employeeDTO = EmployeeDTO.builder()
				.name("anand")
				.age(2)
				.email("anand@madhav.com")
				.role("USER")
				.salary(23400.90)
				.dateOfJoining(LocalDate.of(2023, 5,15))
				.isActive(true)
				.build();

		EmployeeDTO createdEmployee = employeeClient.createNewEmployee(employeeDTO);

		System.out.println(createdEmployee);
	}

}
