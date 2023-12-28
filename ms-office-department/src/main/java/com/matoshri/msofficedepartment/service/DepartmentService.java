package com.matoshri.msofficedepartment.service;

import com.matoshri.msofficedepartment.entity.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> findAll();

    DepartmentDTO findById(Long deptId);

    Long save(DepartmentDTO dto);

}
