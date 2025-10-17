package com.week2.SpringRestCrud.controllers;

import com.week2.SpringRestCrud.dto.EmployeeDto;
import com.week2.SpringRestCrud.entities.EmployeeEntity;
import com.week2.SpringRestCrud.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @SpyBean
    private ModelMapper modelMapper;

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
    public void findEmployeeWithValidId() throws Exception {
        Long id =1L;
        when(employeeService.findById(id)).thenReturn(Optional.of(employeeDto));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(1)
                                    ));

    }
}