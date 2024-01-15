package com.matoshri.employee.service.impl;

import com.matoshri.employee.entity.EmployeeDTO;
import impl.BaseConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class EmployeeServiceImplTest extends BaseConfiguration {

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Test
  public void getAllEmployees() {
    try {
      List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
      assertNotNull(allEmployees);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
