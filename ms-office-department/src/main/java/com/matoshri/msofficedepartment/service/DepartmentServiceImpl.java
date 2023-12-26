package com.matoshri.msofficedepartment.service;

import com.matoshri.msofficedepartment.entity.Department;
import com.matoshri.msofficedepartment.entity.DepartmentDTO;
import com.matoshri.msofficedepartment.exception.ResourceNotFoundException;
import com.matoshri.msofficedepartment.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository deptRepo;


    public DepartmentServiceImpl(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return deptRepo.findAll().stream()
                .map(d -> new DepartmentDTO(d.getDeptId(), d.getDeptName()))
                .toList();
    }

    @Override
    public DepartmentDTO findById(Long deptId) {
        Department department = deptRepo.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException(deptId));
        return new DepartmentDTO(department.getDeptId(), department.getDeptName());
    }

    @Override
    public Long save(DepartmentDTO dto) {
        Department department = new Department();
        department.setDeptName(dto.getDeptName());
        if (!List.of(dto.getEmployees()).isEmpty()) {
            department.setEmployees(dto.getEmployees());
        }
        department = deptRepo.save(new Department(dto.getDeptName()));
        return department.getDeptId();
    }
}
