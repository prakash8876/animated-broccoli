package com.matoshri.msofficedepartment.repository;

import com.matoshri.msofficedepartment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
