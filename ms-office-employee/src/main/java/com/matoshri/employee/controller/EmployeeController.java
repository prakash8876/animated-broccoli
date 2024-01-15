package com.matoshri.employee.controller;

import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "EMPLOYEE_TAG")
@RestController
@RequestMapping(path = {"/employee"})
@Validated
class EmployeeController {

  private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
  private final EmployeeService empService;

  public EmployeeController(EmployeeService empService) {
    this.empService = empService;
  }

  @GetMapping(value = {"/get/all"})
  ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
    log.info("Find all employees");

    try {
      List<EmployeeDTO> employees = this.empService.getAllEmployees();
      return ResponseEntity.ok(employees);
    } catch (ExecutionException | InterruptedException e) {
      log.warn("Interrupted! {}", ExceptionUtils.getStackTrace(e));
      Thread.currentThread().interrupt();
      return ResponseEntity.status(HttpStatus.CONFLICT_409).build();
    }
  }

  @PostMapping(value = {"/save"})
  ResponseEntity<Long> saveEmployee(@RequestBody @Valid EmployeeDTO dto) {
    log.info("Save employee {}", dto);
    long empId = this.empService.saveEmployee(dto);
    return ResponseEntity.created(URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/save/").toUriString())).body(empId);
  }

  @GetMapping(value = {"/get/byid/{employee-id}"})
  ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employee-id") Long empId) {
    log.info("Find employee by ID {}", empId);
    EmployeeDTO employeeById = this.empService.getEmployeeById(empId);
    return ResponseEntity.ok(employeeById);
  }
}
