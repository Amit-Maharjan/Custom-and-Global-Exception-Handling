package com.maharjan.exceptionHandling.controllers;

import com.maharjan.exceptionHandling.customExceptions.BusinessException;
import com.maharjan.exceptionHandling.customExceptions.ControllerException;
import com.maharjan.exceptionHandling.entity.Employee;
import com.maharjan.exceptionHandling.services.CustomExceptionEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class CustomExceptionEmployeeController {
    @Autowired
    private CustomExceptionEmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch (BusinessException ex) {
            ControllerException ce = new ControllerException(ex.getErrorCode(), ex.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ControllerException ce = new ControllerException("612","Something went wrong in controller.");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            Employee newEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
        } catch (BusinessException ex) {
            ControllerException ce = new ControllerException(ex.getErrorCode(), ex.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ControllerException ce = new ControllerException("611","Something went wrong in controller.");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
