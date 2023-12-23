package com.matoshri.employee.controller;

import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.service.EmployeeService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/office/employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService empService;

    @Autowired
    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = new ArrayList<>();
        try {
            employees = empService.getAllEmployees();
            log.info("Fetched {} employees", employees.size());
        } catch (ExecutionException | InterruptedException e) {
            log.error("Exception: {}", ExceptionUtils.getStackTrace(e));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employees);
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveEmployee(@RequestBody @Validated EmployeeDTO dto) {
        long empId = empService.saveEmployee(dto);
        log.info("saved employee with id {}", empId);
        return ResponseEntity.status(HttpStatus.CREATED).body(empId);
    }

    @GetMapping("/get/byid/{employee-id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employee-id") Long empId) {
        EmployeeDTO employeeById = empService.getEmployeeById(empId);
        log.info("Fetched employee of ID {}", employeeById.getEmpId());
        return ResponseEntity.ok(employeeById);
    }
}
