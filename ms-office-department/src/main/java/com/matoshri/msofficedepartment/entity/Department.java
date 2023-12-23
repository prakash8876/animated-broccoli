package com.matoshri.msofficedepartment.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @SequenceGenerator(name = "dept_seq", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
    @Column(name = "deptId")
    private Long deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_employees")
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(Long deptId, String deptName, List<Employee> employees) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.employees = employees;
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
    public String toString() {
        return new StringBuilder("Department{")
                .append("deptId=" + deptId)
                .append(", deptName='" + deptName)
                .append('}').toString();
    }
}
