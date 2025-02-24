package com.week2.SpringRestCrud.services;

import com.week2.SpringRestCrud.TestContainerConfiguration;
import com.week2.SpringRestCrud.dto.EmployeeDto;
import com.week2.SpringRestCrud.entities.EmployeeEntity;
import com.week2.SpringRestCrud.repositorys.EmployeeRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeService employeeService;

    public EmployeeEntity mockEmployee;
    private EmployeeDto mockedEmployeeDto;

    @BeforeEach
    void setup(){
        mockEmployee = EmployeeEntity.builder()
                .id(1L)
                .age(25)
                .name("venu")
                .email("venumadhav@gmail.com")
                .salary(25000.00)
                .role("ADMIN")
                .dateOfJoining(LocalDate.now())
                .isActive(true)
                .build();

        mockedEmployeeDto = modelMapper.map(mockEmployee , EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsPresent_ThenReturnEmployeeDto(){


        //assign
        Long id = mockEmployee.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee)); //stubbing
        when(employeeRepository.existsById(id)).thenReturn(true);

//        EmployeeEntity savedEmployee = employeeRepository.save(mockEmployee);
//        System.out.println(savedEmployee);

        //act
        Optional<EmployeeDto> employeeDto = employeeService.findById(id);

        //assert
        assertThat(employeeDto.get().getId()).isEqualTo(mockEmployee.getId());
        assertThat(employeeDto.get().getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepository , atLeastOnce()).findById(id);

    }

    @Test
    void testSaveEmployee_WhenEmployeeEmailIsNotPresent_ThenReturnTheSavedEmployee(){
        //assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(mockEmployee);

        //act
        EmployeeDto employeeDto = employeeService.saveEmployee(mockedEmployeeDto);

        //assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockedEmployeeDto.getEmail());

        ArgumentCaptor<EmployeeEntity> entityArgumentCaptor = ArgumentCaptor.forClass(EmployeeEntity.class);
        verify(employeeRepository,atLeastOnce()).save(entityArgumentCaptor.capture());

        EmployeeEntity employeeEntity = entityArgumentCaptor.getValue();

        assertThat(employeeEntity.getEmail()).isEqualTo(mockedEmployeeDto.getEmail());
    }
}