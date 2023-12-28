package com.matoshri.msofficedepartment.entity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDTO {
        private Long deptId;
        private String deptName;
        private List<Employee> employees = new ArrayList<>();

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long deptId, String deptName) {
        this.deptId = deptId;
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
}