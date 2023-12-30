package com.matoshri.employee.service.impl;

import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.exception.EmployeeNotFoundException;
import com.matoshri.employee.repo.EmployeeRepository;
import com.matoshri.employee.service.EmployeeService;
import com.matoshri.employee.util.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Transactional(readOnly = true)
class EmployeeServiceImpl implements EmployeeService {

  private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  private final EmployeeRepository empRepo;
  private final EmployeeMapper mapper;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository empRepo, EmployeeMapper mapper) {
    this.empRepo = empRepo;
    this.mapper = mapper;
  }

  @Override
  public List<EmployeeDTO> getAllEmployees() throws ExecutionException, InterruptedException {
    log.info("Getting all employees");
    List<EmployeeDTO> employeeDTOS;
    CompletableFuture<List<EmployeeDTO>> cf =
        CompletableFuture.supplyAsync(
            () -> {
              List<Employee> list = empRepo.findAll();
              return list.parallelStream().map(mapper::mapToDTO).toList();
            });
    employeeDTOS = cf.get();
    return employeeDTOS;
  }

  @Override
  @Transactional
  public Long saveEmployee(EmployeeDTO dto) {
    log.info("Saving employee {}", dto);
    Employee emp = new Employee(dto.empName(), dto.empEmail(), dto.depId());
    emp = empRepo.save(emp);
    return emp.getEmpId();
  }

  @Override
  public EmployeeDTO getEmployeeById(Long empId) {
    log.info("Getting employee of ID {}", empId);
    Employee employee =
        empRepo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
    return new EmployeeDTO(
        employee.getEmpId(), employee.getEmpName(), employee.getEmpEmail(), employee.getDepId());
  }
}
