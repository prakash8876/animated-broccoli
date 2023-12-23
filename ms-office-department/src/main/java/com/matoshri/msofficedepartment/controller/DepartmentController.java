package com.matoshri.msofficedepartment.controller;

import com.matoshri.msofficedepartment.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/office/department")
public class DepartmentController {
    private final DepartmentService deptService;
}
