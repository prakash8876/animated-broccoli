package com.matoshri.employee.entity;

import jakarta.validation.constraints.Email;

import java.io.Serializable;

public record EmployeeDTO(
    Long empId,
    String empName,
    @Email(message = "Enter proper email address.") String empEmail,
    Long depId)
    implements Serializable {}
