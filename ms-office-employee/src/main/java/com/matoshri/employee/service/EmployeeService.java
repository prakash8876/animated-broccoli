package com.matoshri.employee.service;

import com.matoshri.employee.entity.EmployeeDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface EmployeeService {
  List<EmployeeDTO> getAllEmployees() throws ExecutionException, InterruptedException;

  Long saveEmployee(EmployeeDTO dto);

  EmployeeDTO getEmployeeById(Long empId);

  void generateReport();
}
