package com.example.venu.cacheapp.dto;

import com.example.venu.cacheapp.annotations.EmployeeRoleValidation;
import com.example.venu.cacheapp.utils.IntegerValidatorDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto implements Serializable {

    private  Long Id;

    @NotBlank(message ="name of the employee cannot be null or blank")
    @Size(min = 4 , max = 10 , message = "number of character name should be range of [4 ,10]")
    private String name;

    @NotBlank(message ="email of the employee cannot be null or blank")
    @Email(message = "email should be proper email format only....")
    private String email;

    @NotNull(message ="age of the employee cannot be null")
    @Max(value = 80 , message = "employee age cannot grater than 80")
    @Min(value =18 , message = "employee age cannot less than 18")
    @JsonDeserialize(using = IntegerValidatorDeserializer.class)
    private Integer age;

    @NotBlank(message ="role of the employee cannot be null or blank")
//    @Pattern(regexp = "^(USER|ADMIN)$" , message = "employee role should be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; //ADMIN , USER

    @NotNull(message ="salary of the employee cannot be null")
    @Positive(message = "salary of employee should be positive")
    @Digits(integer = 6 , fraction = 2 , message = "salary of employee should be XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "9999.99")
    private Double salary;

    @PastOrPresent(message = "date of joining of employee should be past or present date not in future")
//    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "employee should be active only needed")
    @JsonProperty("isActive")
    private Boolean isActive;

}
