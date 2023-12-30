package com.matoshri.msofficedepartment.service.impl;

import com.google.gson.GsonBuilder;
import com.matoshri.msofficedepartment.entity.Department;
import com.matoshri.msofficedepartment.entity.DepartmentDTO;
import com.matoshri.msofficedepartment.exception.ResourceNotFoundException;
import com.matoshri.msofficedepartment.repository.DepartmentRepository;
import com.matoshri.msofficedepartment.repository.EmployeeRepository;
import com.matoshri.msofficedepartment.service.DepartmentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
  private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
  private final DepartmentRepository deptRepo;
  private final EmployeeRepository empRepo;

  @Autowired
  public DepartmentServiceImpl(DepartmentRepository deptRepo, EmployeeRepository empRepo) {
    this.deptRepo = deptRepo;
    this.empRepo = empRepo;
  }

  @Override
  public List<DepartmentDTO> findAll() {
    return deptRepo.findAll().parallelStream()
        .map(d -> new DepartmentDTO(d.getDeptId(), d.getDeptName()))
        .toList();
  }

  @Override
  public DepartmentDTO findById(Long deptId) {
    Department department =
        deptRepo.findById(deptId).orElseThrow(() -> new ResourceNotFoundException(deptId));
    return new DepartmentDTO(department.getDeptId(), department.getDeptName());
  }

  @Override
  public Long save(DepartmentDTO dto) {
    Department department = new Department();
    department.setDeptName(dto.getDeptName());
    if (!List.of(dto.getEmployees()).isEmpty()) {
      department.setEmployees(dto.getEmployees());
    }
    empRepo.saveAll(department.getEmployees());
    department = deptRepo.save(new Department(dto.getDeptName()));
    return department.getDeptId();
  }

  @Override
  public void generateReport() {
    log.info("generating report of employees ...");
    List<DepartmentDTO> employeeDTOS =
        deptRepo.findAll().stream()
            .map(d -> new DepartmentDTO(d.getDeptId(), d.getDeptName()))
            .toList();
    Path path = Paths.get("src/main/resources/data/department_report_" + LocalDate.now() + ".json");
    try {
      String jsonData =
          new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(employeeDTOS);
      Files.write(path, jsonData.getBytes());
    } catch (IOException e) {
      log.error("error while writing to path {}", path, e);
    }
    log.info("generated report at {}", path);
  }
}
