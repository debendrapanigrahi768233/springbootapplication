package com.deb.springWebMvc.springWebMvc.services;

import com.deb.springWebMvc.springWebMvc.dto.EmployeeDTO;
import com.deb.springWebMvc.springWebMvc.entities.EmployeeEntity;
import com.deb.springWebMvc.springWebMvc.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeByID(Long id) {
        Optional<EmployeeEntity> em=  employeeRepository.findById(id);
        return em.map(employee -> modelMapper.map(employee,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> em=  employeeRepository.findAll();
        return em
        .stream()
        .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
        .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity em= employeeRepository.save(modelMapper.map(inputEmployee, EmployeeEntity.class));
        return modelMapper.map(em, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO newEmployeeDetails, Long employeeId){
        EmployeeEntity em = modelMapper.map(newEmployeeDetails, EmployeeEntity.class);
        em.setId(employeeId);
        EmployeeEntity newEm = employeeRepository.save(em);
        return modelMapper.map(newEm,EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists)return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeDetailsById(Map<String, Object> newEmployeeDetails, Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists)return null;
        EmployeeEntity em=  employeeRepository.findById(id).orElse(null);
        newEmployeeDetails.forEach((key,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, key);
            assert fieldToBeUpdated != null;
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, em, value);
        });
        assert em != null;
        return modelMapper.map(employeeRepository.save(em), EmployeeDTO.class);
    }
}
