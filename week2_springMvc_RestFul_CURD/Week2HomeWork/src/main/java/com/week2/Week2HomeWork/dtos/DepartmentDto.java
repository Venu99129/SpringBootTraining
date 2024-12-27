package com.week2.Week2HomeWork.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    private Long id;

    @NotEmpty(message = "department title cannot be null ")
    @NotBlank(message = "department title cannot be blank ")
    @Size(min = 4 , max = 12 ,message = "department tittle should size range of [4 , 12]")
    private String title;

    @NotEmpty(message = "department isActive cannot be null or empty")
    @AssertTrue(message = "department active state must should be active true only")
    private Boolean isActive;


    @NotEmpty(message = "department isActive cannot be null or empty")
    @PastOrPresent(message = "department createdAt date should be past or present date only")
    private LocalDateTime createdAt;

    @CreditCardNumber(message = "department credit-card gives proper credit-card number")
    private String linkedCreditCard;

    @URL(message = "department url should be correct format")
    private String linkedUrl;

}
