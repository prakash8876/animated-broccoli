package com.matoshri.msofficedepartment.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {

  @Id
  @SequenceGenerator(
      name = "dept_seq",
      sequenceName = "dept_seq",
      allocationSize = 1,
      initialValue = 1000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
  @Column(name = "deptId", nullable = false, updatable = false)
  private Long deptId;

  @Column(name = "dept_name")
  private String deptName;

  @OneToMany
  @Column(name = "dept_employees")
  private List<Employee> employees = new ArrayList<>();

  public Department() {}

  public Department(String deptName) {
    this.deptName = deptName;
  }

  public Long getDeptId() {
    return deptId;
  }

  public void setDeptId(Long deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Department that = (Department) o;
    return Objects.equals(deptId, that.deptId) && Objects.equals(deptName, that.deptName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deptId, deptName);
  }

  @Override
  public String toString() {
    return new StringBuilder("Department{")
        .append("deptId=" + deptId)
        .append(", deptName='" + deptName)
        .append('}')
        .toString();
  }
}
