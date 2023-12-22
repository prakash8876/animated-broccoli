package com.matoshri.employee.entity;

import jakarta.validation.constraints.Email;

public class EmployeeDTO {
    private Long empId;
    private String empEmail;
    @Email(message = "Enter proper email address.")
    private String empName;
    private Long depId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String empEmail, String empName, Long depId) {
        this.empEmail = empEmail;
        this.empName = empName;
        this.depId = depId;
    }

    public EmployeeDTO(Long empId, String empEmail, String empName, Long depId) {
        this.empId = empId;
        this.empEmail = empEmail;
        this.empName = empName;
        this.depId = depId;
    }

    @Override
    public String toString() {
        return new StringBuilder("Employee{")
                .append("empId=").append(empId)
                .append(", empEmail='").append(empEmail)
                .append(", empName='").append(empName)
                .append(", depId=").append(depId)
                .append('}').toString();
    }
}
