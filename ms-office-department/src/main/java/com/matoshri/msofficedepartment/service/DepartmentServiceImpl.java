package com.matoshri.msofficedepartment.service;

import com.matoshri.msofficedepartment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository deptRepo;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }
}
