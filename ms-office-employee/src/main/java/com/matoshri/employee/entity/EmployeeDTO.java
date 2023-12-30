package com.matoshri.employee.entity;

import jakarta.validation.constraints.Email;

import java.io.Serializable;

public record EmployeeDTO(
    Long empId,
    String empName,
    @Email String empEmail,
    Long depId)
    implements Serializable {}
