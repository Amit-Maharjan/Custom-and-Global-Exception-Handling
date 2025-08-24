package com.maharjan.exceptionHandling.services;

import com.maharjan.exceptionHandling.customExceptions.BusinessException;
import com.maharjan.exceptionHandling.entity.Employee;
import com.maharjan.exceptionHandling.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalExceptionEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            throw new BusinessException("604","The list is empty. Nothing to return.");
        }
        return employeeList;
    }

    public Employee saveEmployee(Employee employee) {
        if (employee.getName().isEmpty()) {
            throw new BusinessException("601","Please send proper name. It is currently blank.");
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}