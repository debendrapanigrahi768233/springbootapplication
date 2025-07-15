package com.deb.springWebMvc.springWebMvc.repositories;

import com.deb.springWebMvc.springWebMvc.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
