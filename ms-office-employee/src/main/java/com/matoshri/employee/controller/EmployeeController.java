package com.matoshri.employee.controller;

import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "EMPLOYEE_TAG")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
  private final EmployeeService empService;

  @Autowired
  public EmployeeController(EmployeeService empService) {
    this.empService = empService;
  }

  @GetMapping(value = "/get/all")
  ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
    List<EmployeeDTO> employees = new ArrayList<>();
    try {
      employees = empService.getAllEmployees();
      log.info("Fetched {} employees", employees.size());
    } catch (ExecutionException | InterruptedException e) {
      log.warn("Interrupted! {}", ExceptionUtils.getStackTrace(e));
      Thread.currentThread().interrupt();
    }
    return ResponseEntity.ok(employees);
  }

  @PostMapping(value = "/save")
  ResponseEntity<Long> saveEmployee(@RequestBody @Validated EmployeeDTO dto) {
    long empId = empService.saveEmployee(dto);
    log.info("saved employee with id {}", empId);
    return ResponseEntity.status(HttpStatus.CREATED).body(empId);
  }

  @GetMapping(value = "/get/byid/{employee-id}")
  ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employee-id") Long empId) {
    EmployeeDTO employeeById = empService.getEmployeeById(empId);
    log.info("Fetched employee of ID {}", employeeById.empId());
    return ResponseEntity.ok(employeeById);
  }
}
