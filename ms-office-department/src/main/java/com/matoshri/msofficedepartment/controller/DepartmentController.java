package com.matoshri.msofficedepartment.controller;

import com.matoshri.msofficedepartment.entity.DepartmentDTO;
import com.matoshri.msofficedepartment.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService deptService;

    @Autowired
    public DepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    List<DepartmentDTO> getAllDepartments() {
        log.info("Getting departments ...");
        return deptService.findAll();
    }

    @GetMapping(value = "/byid/{dept-id}")
    @ResponseStatus(HttpStatus.OK)
    DepartmentDTO getById(@PathVariable("dept-id") Long deptId) {
        log.info("Getting Department by ID {} ...", deptId);
        return deptService.findById(deptId);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    Long saveDepartment(@RequestBody @Validated DepartmentDTO dto) {
        log.info("Saving department {} ...", dto);
        return deptService.save(dto);
    }
}
