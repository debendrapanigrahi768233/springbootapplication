package com.deb.springWebMvc.springWebMvc.dto;

import com.deb.springWebMvc.springWebMvc.entities.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;

}
