package com.matoshri.employee.controller;

import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/office/employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService empService;

    @Autowired
    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = new ArrayList<>();
        try {
            employees = empService.getAllEmployees();
        } catch (Exception e) {
            log.error("Exception: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employees);
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveEmployee(@RequestBody @Validated EmployeeDTO dto) {
        Long empId = empService.saveEmployee(dto);
        return ResponseEntity.ok(empId);
    }
}
