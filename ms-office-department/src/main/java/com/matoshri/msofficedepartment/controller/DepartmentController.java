package com.matoshri.msofficedepartment.controller;

import com.matoshri.msofficedepartment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService deptService;

    @Autowired
    public DepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }
}
