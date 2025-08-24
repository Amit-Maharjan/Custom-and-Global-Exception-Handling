package com.maharjan.exceptionHandling.services;

import com.maharjan.exceptionHandling.customExceptions.BusinessException;
import com.maharjan.exceptionHandling.entity.Employee;
import com.maharjan.exceptionHandling.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomExceptionEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        try {
            return employeeRepository.findById(id).get();
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("606","The given id is not valid " + ex.getMessage());
        } catch (NoSuchElementException ex) {
            throw new BusinessException("607","Employee not found " + ex.getMessage());
        } catch (Exception ex) {
            throw new BusinessException("609","Something went wrong in service layer while fetching employee " + ex.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList;
        try {
            employeeList = employeeRepository.findAll();
        } catch (Exception ex) {
            throw new BusinessException("605","Something went wrong in service layer while fetching all employees " + ex.getMessage());
        }

        if (employeeList.isEmpty()) {
            throw new BusinessException("604","The list is empty. Nothing to return.");
        }
        return employeeList;
    }

    public Employee saveEmployee(Employee employee) {
        if (employee.getName().isEmpty()) {
            throw new BusinessException("601","Please send proper name. It is currently blank.");
        }

        try {
            return employeeRepository.save(employee);
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("602","Given employee is null " + ex.getMessage());
        } catch (Exception ex) {
            throw new BusinessException("603","Something went wrong in service layer while adding a new employee " + ex.getMessage());
        }
    }

    public void deleteEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("608","The given id is not valid " + ex.getMessage());
        } catch (Exception ex) {
            throw new BusinessException("610","Something went wrong in service layer while deleting employee " + ex.getMessage());
        }
    }
}
