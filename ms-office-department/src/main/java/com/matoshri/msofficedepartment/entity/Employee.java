package com.matoshri.msofficedepartment.entity;

import jakarta.validation.constraints.Email;

public class Employee {
    private Long empId;

    private String empName;
    @Email(message = "Enter proper email address.")
    private String empEmail;
    private Long depId;

    public Employee() {
    }

    public Employee(Long empId, String empName, String empEmail, Long depId) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.depId = depId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return new StringBuilder("Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", depId=" + depId +
                '}').toString();
    }
}
