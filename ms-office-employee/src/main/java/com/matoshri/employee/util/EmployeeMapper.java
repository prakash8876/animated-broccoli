package com.matoshri.employee.util;

import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.entity.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  private EmployeeMapper() {}

  public static Employee mapToEmployee(EmployeeDTO dto) {
//    if (nonNull(dto.empId())) {
//      return new Employee(dto.empId(), dto.empName(), dto.empEmail(), dto.depId());
//    } else {
//      return new Employee(dto.empName(), dto.empEmail(), dto.empId());
//    }
    Employee employee = new Employee();
    BeanUtils.copyProperties(dto, employee);
    return employee;
  }

  public static EmployeeDTO mapToDTO(Employee emp) {
//    return new EmployeeDTO(emp.getEmpId(), emp.getEmpName(), emp.getEmpEmail(), emp.getDepId());
    EmployeeDTO dto = new EmployeeDTO();
    BeanUtils.copyProperties(emp, dto);
    return dto;
  }
}
