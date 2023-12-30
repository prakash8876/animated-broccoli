package com.matoshri.employee.util;

import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.entity.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmployeeMapper {

  private EmployeeMapper() {}

  public Employee mapToEmployee(EmployeeDTO dto) {
    if (Objects.nonNull(dto.empId())) {
      return new Employee(dto.empId(), dto.empName(), dto.empEmail(), dto.depId());
    } else {
      return new Employee(dto.empName(), dto.empEmail(), dto.empId());
    }
  }

  public EmployeeDTO mapToDTO(Employee emp) {
    return new EmployeeDTO(emp.getEmpId(), emp.getEmpName(), emp.getEmpEmail(), emp.getDepId());
  }
}
