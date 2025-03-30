package com.week2.SpringRestCrud.services;

import com.week2.SpringRestCrud.Exceptions.ResourceNotFoundException;
import com.week2.SpringRestCrud.dto.EmployeeDto;
import com.week2.SpringRestCrud.entities.EmployeeEntity;
import com.week2.SpringRestCrud.repositorys.EmployeeRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


//@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
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
    void testGetEmployeeById_whenEmployeeIdIsNotPresent_thenReturnResourceNotFoundException(){
        //assign
        when(employeeRepository.existsById(anyLong())).thenReturn(false);
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        //assert
        assertThatThrownBy(()-> employeeService.findById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("no such employee fount with id :1");

        verify(employeeRepository, atLeastOnce()).existsById(1L);

    }

    @Test
    void testSaveEmployee_whenEmployeeEmailIsPresent_ThenReturnRunTimeException(){
        //assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of(mockEmployee));

        assertThatThrownBy(()-> employeeService.isExistingByEmail("venumadhav@gmail.com")
                ).isInstanceOf(RuntimeException.class)
                .hasMessage("employee with this email: venumadhav@gmail.com all ready existed");
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

    @Test
    void testUpdateEmployee_WhenEmployeeIsExists_thenUpdateEmployeeDetails(){
        //assign
        mockedEmployeeDto.setName("ramu");
        mockedEmployeeDto.setRole("USER");
        EmployeeEntity employee = modelMapper.map(mockedEmployeeDto, EmployeeEntity.class);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employee);
        when(employeeRepository.existsById(mockedEmployeeDto.getId())).thenReturn(true);

        //act
        EmployeeDto updatedEmployee = employeeService.updateEmployee(mockedEmployeeDto.getId(),mockedEmployeeDto);

        assertThat(updatedEmployee)
                .isEqualTo(mockedEmployeeDto);

        verify(employeeRepository,times(1)).save(any(EmployeeEntity.class));
    }
    @Test
    void testUpdatePartialEmployee_whenEmployeeIsExistsAndFilesNotMatched_ThenReturnIllegalArgumentException(){
        //assign
        Map<String,Object> updatableFiles = Map.of("name","gopal","aeg",32);
        when(employeeRepository.existsById(mockedEmployeeDto.getId())).thenReturn(true);
        when(employeeRepository.findById(mockedEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));

        //assert
        assertThatThrownBy(()->employeeService.updatePartialEmployee(mockedEmployeeDto.getId(),updatableFiles))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unable to find field aeg on class com.week2.SpringRestCrud.entities.EmployeeEntity");

        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository,never()).save(any(EmployeeEntity.class));

    }

    @Test
    void testUpdatePartialEmployee_WhenEmployeeIsExistsAndFieldsMatched_thenUpdateEmployee(){
        //assign
        Map<String,Object> updatableFiles = Map.of("name","gopal","age",32);
        when(employeeRepository.existsById(mockedEmployeeDto.getId())).thenReturn(true);
        when(employeeRepository.findById(mockedEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockedEmployeeDto.setName("gopal");
        mockedEmployeeDto.setAge(32);
        EmployeeEntity employee = modelMapper.map(mockedEmployeeDto, EmployeeEntity.class);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employee);

        //act
        EmployeeDto updatedEmployeeDto = employeeService.updatePartialEmployee(mockedEmployeeDto.getId(),updatableFiles);

        //assert
        assertThat(mockedEmployeeDto).isEqualTo(updatedEmployeeDto);
        verify(employeeRepository).save(any(EmployeeEntity.class));
    }

    @Test
    void testUpdatePartialEmployee_WhenEmployeeDoesNotExist_ThenThrowException() {
        // Arrange
        when(employeeRepository.existsById(1L)).thenReturn(false); // Mock existsById to return false

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.updatePartialEmployee(1L, new HashMap<>());
        });

        // Verify interactions
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, never()).findById(anyLong());
        verify(employeeRepository, never()).save(any(EmployeeEntity.class));
    }

    @Test
    void testUpdatePartialEmployee_WhenInvalidField_ThenThrowException() {
        // Arrange
        Map<String, Object> updates = new HashMap<>();
        updates.put("invalidField", "Invalid Value");

        // Mock the behavior of existsById and findById
        when(employeeRepository.existsById(1L)).thenReturn(true); // Ensure existsById returns true
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee)); // Mock findById to return the employee

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.updatePartialEmployee(1L, updates);
        });

        // Verify interactions
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(EmployeeEntity.class));
    }

    @Test
    void testUpdatePartialEmployee_WhenReflectionUtilsThrowsException_ThenThrowException() {
        // Arrange
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Jane Doe");
        when(employeeRepository.existsById(1L)).thenReturn(true);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
        doThrow(new RuntimeException("Reflection failed")).when(employeeRepository).save(any(EmployeeEntity.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            employeeService.updatePartialEmployee(1L, updates);
        });
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }


}