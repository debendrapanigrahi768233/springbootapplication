package com.deb.springWebMvc.springWebMvc.dto;

import com.deb.springWebMvc.springWebMvc.annotations.EmployeeRoleValidation;
import com.deb.springWebMvc.springWebMvc.entities.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//This is like a POJO class (Plain Old Java Object) which are used to defined the DTO
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotNull(message="Required field in Employee: name")
    @NotBlank(message = "Name is a mandatory field")
    @Size(min=3,max=18,message="Name field character length should be under [3,18]")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Valid email is required")
    private String email;


    @NotNull(message = "Age is mandatory field")
    @Max(value = 60, message = "Max value allowed is 60")
    @Min(value = 18, message = "Minimum value allowed is 18")
    private Integer age;


    @NotBlank(message = "Role cannot be blank")
    @EmployeeRoleValidation
    private String role;


    @PastOrPresent(message="Date of Joining can be today or a past day")
    private LocalDate dateOfJoining;


    @JsonProperty("isActive")
    private Boolean isActive;}
