package com.maharjan.exceptionHandling.repository;

import com.maharjan.exceptionHandling.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
