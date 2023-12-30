package com.matoshri.msofficedepartment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long empId;

  private String empName;

  @Email(message = "Enter proper email address.")
  private String empEmail;

  private Long depId;

  public Employee() {}

  public Employee(String empName, String empEmail, Long depId) {
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
}
