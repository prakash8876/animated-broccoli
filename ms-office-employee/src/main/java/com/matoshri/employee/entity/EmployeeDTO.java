package com.matoshri.employee.entity;

import jakarta.validation.constraints.Email;

import java.io.Serial;
import java.io.Serializable;

public class EmployeeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7187928829922407535L;
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

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
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
