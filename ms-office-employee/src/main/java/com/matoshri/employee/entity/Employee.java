package com.matoshri.employee.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;

@JsonInclude(NON_DEFAULT)
@Entity
@Table(name = "employees")
public class Employee {
  @Id
  @SequenceGenerator(
      name = "emp_seq",
      sequenceName = "emp_seq",
      allocationSize = 1,
      initialValue = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
  @Column(name = "empId", unique = true, updatable = false)
  private Long empId;

  @NotEmpty(message = "Email can't be empty")
  @Column(name = "empEmail", length = 50)
  private @Email String empEmail;

  @NotEmpty(message = "Name can't be empty")
  @Column(name = "empName", length = 20)
  private String empName;

  @Column(name = "emp_dep_id")
  private Long depId;

  public Employee() {}

  public Employee(String empName, String empEmail, Long depId) {
    this.empEmail = empEmail;
    this.empName = empName;
    this.depId = depId;
  }

  public Employee(Long empId, String empName, String empEmail, Long depId) {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(empId, employee.empId)
        && Objects.equals(empEmail, employee.empEmail)
        && Objects.equals(empName, employee.empName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(empId, empEmail, empName);
  }

  @Override
  public String toString() {
    return new StringBuilder("Employee{")
        .append(" empId=")
        .append(empId)
        .append(", empName='")
        .append(empName)
        .append(", empEmail='")
        .append(empEmail)
        .append(", depId=")
        .append(depId)
        .append('}')
        .toString();
  }
}
