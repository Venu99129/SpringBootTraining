package com.week2.SpringRestCrud.controllers;

import com.week2.SpringRestCrud.advices.ApiResponse;
import com.week2.SpringRestCrud.dto.EmployeeDto;
import com.week2.SpringRestCrud.entities.EmployeeEntity;
import com.week2.SpringRestCrud.repositorys.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

//@Import(TestContainerConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
class EmployeeControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employeeEntity;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setup(){
        employeeEntity = EmployeeEntity.builder()
                .id(1L)
                .age(25)
                .name("venu")
                .email("venumadhav@gmail.com")
                .salary(25000.00)
                .role("ADMIN")
                .dateOfJoining(LocalDate.now())
                .isActive(true)
                .build();

        employeeDto = EmployeeDto.builder()
                .id(1L)
                .age(25)
                .name("venu")
                .email("venumadhav@gmail.com")
                .salary(25000.00)
                .role("ADMIN")
                .dateOfJoining(LocalDate.now())
                .isActive(true)
                .build();
    }

    @Test
    void testGetEmployeeById_success(){
        EmployeeEntity testEmployee = employeeRepository.save(employeeEntity);
        webTestClient.get()
                .uri("/employees/{id}",testEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApiResponse.class)
                .value(apiResponse -> {
                    System.out.println(apiResponse.getData());
                    EmployeeDto responseDto = modelMapper.map(apiResponse.getData(), EmployeeDto.class);
                    assertThat(responseDto).isEqualTo(employeeDto);
                });

    }

}