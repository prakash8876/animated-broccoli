package com.matoshri.msofficedepartment.repository;

import com.matoshri.msofficedepartment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
