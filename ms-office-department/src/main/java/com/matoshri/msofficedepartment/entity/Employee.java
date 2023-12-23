package com.matoshri.msofficedepartment.entity;

import jakarta.validation.constraints.Email;

public record Employee(Long empId, String empName, @Email(message = "Enter proper email address.") String empEmail,
                       Long depId) {
}