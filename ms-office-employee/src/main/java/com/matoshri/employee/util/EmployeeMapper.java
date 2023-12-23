package com.matoshri.employee.util;

import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.entity.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmployeeMapper {

    private EmployeeMapper() {}

    public Employee mapToEmployee(EmployeeDTO dto) {
        if (Objects.nonNull(dto.getEmpId())) {
            return new Employee(dto.getEmpId(), dto.getEmpName(), dto.getEmpEmail(), dto.getDepId());
        } else {
            return new Employee(dto.getEmpName(), dto.getEmpEmail(), dto.getDepId());
        }
    }

    public EmployeeDTO mapToDTO(Employee emp) {
        return new EmployeeDTO(emp.getEmpId(), emp.getEmpName(), emp.getEmpEmail(), emp.getDepId());
    }
}
