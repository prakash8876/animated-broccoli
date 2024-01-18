package com.matoshri.employee.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.exception.EmployeeNotFoundException;
import com.matoshri.employee.repo.EmployeeRepository;
import com.matoshri.employee.service.EmployeeService;
import com.matoshri.employee.util.EmployeeMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
class EmployeeServiceImpl implements EmployeeService {

  private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  private static final Gson GSON =
      new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

  @Value("${data.path}")
  private String dataPath;
  private final EmployeeRepository empRepo;

  public EmployeeServiceImpl(EmployeeRepository empRepo) {
    this.empRepo = empRepo;
  }

  @Override
  public List<EmployeeDTO> getAllEmployees() throws ExecutionException, InterruptedException {
    List<EmployeeDTO> employeeDTOS;
    CompletableFuture<List<EmployeeDTO>> cf = CompletableFuture.supplyAsync(() -> {
      List<Employee> list = empRepo.findAll();
      return list.parallelStream().map(EmployeeMapper::mapToDTO).toList();
    });
    employeeDTOS = cf.get();
    log.info("found employees {}", employeeDTOS.size());
    return employeeDTOS;
  }

  @Override
  @Transactional
  public Long saveEmployee(EmployeeDTO dto) {
    Employee emp = new Employee(dto.getEmpName(), dto.getEmpEmail().trim().toLowerCase(), dto.getDepId());
    emp = empRepo.save(emp);
    EmployeeDTO saved = EmployeeMapper.mapToDTO(emp);
    log.info("Saved employee {}", saved);
    return saved.getEmpId();
  }

  @Override
  public EmployeeDTO getEmployeeById(Long empId) {
    Employee employee;
    employee = empRepo.findById(empId)
                      .orElseThrow(() -> {
                        log.error("Employee ID {} doesn't exists", empId);
                        return new EmployeeNotFoundException(empId);
                      });

    EmployeeDTO dto = new EmployeeDTO(employee.getEmpId(), employee.getEmpName(), employee.getEmpEmail(), employee.getDepId());
    log.info("found employee of ID {}", dto.getEmpId());
    return dto;
  }

  @Override
  public void generateReport() {
    log.info("generating report of employees ...");
    Path path = null;

    try {
      List<EmployeeDTO> employeeDTOS = getAllEmployees();
      path = Paths.get(dataPath + "employee_report_" + LocalDate.now() + ".json");
      if (!Files.exists(path.getParent())) {
        log.error("Path doesn't exists {}", path.getParent());
      }

      String jsonData = GSON.toJson(employeeDTOS);
      Files.write(path, jsonData.getBytes());
    } catch (IOException e) {
      log.error("error while writing to path {}", path, e);
    } catch (InterruptedException | ExecutionException e) {
      log.warn("Interrupted! {}", ExceptionUtils.getStackTrace(e));
    }

    log.info("generated report at {}", path);
  }
}
