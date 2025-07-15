package com.deb.springWebMvc.springWebMvc.controllers;

import com.deb.springWebMvc.springWebMvc.dto.EmployeeDTO;
import com.deb.springWebMvc.springWebMvc.entities.EmployeeEntity;
import com.deb.springWebMvc.springWebMvc.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class  EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    //employeeID is a variable here pass as argument to the function/method
//    @GetMapping(path= "/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable(name = "employeeID") Long id){
//    //return new EmployeeDTO(employeeID,"Deb","deb@gmail.com",27, LocalDate.now(),true);
//        return employeeService.getEmployeeByID(id);
//    }

    //employeeID is a variable here pass as argument to the function/method
    @GetMapping(path= "/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name = "employeeID") Long id){
        Optional<EmployeeDTO> em = employeeService.getEmployeeByID(id);
        return em.map(employee -> ResponseEntity.ok(employee)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path= "")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees()); //Same as --> return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO em = employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(em, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO newEmployeeDetails, @PathVariable(name="employeeID") Long Id){
        return ResponseEntity.ok(employeeService.updateEmployeeById(newEmployeeDetails, Id));
    }

    @DeleteMapping(path = "/{employeeID}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name="employeeID") Long Id){
        boolean isDeleted = employeeService.deleteEmployeeById(Id);
        if(isDeleted)return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeDetailsById(@RequestBody Map<String, Object> newEmployeeDetails, @PathVariable(name="employeeID") Long Id){
        EmployeeDTO employeePatched = employeeService.updatePartialEmployeeDetailsById(newEmployeeDetails, Id);
        if(employeePatched!=null){
            return ResponseEntity.ok(employeePatched);
        }
        return ResponseEntity.notFound().build();
    }
}
