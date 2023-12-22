package com.matoshri.employee.service;

import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.entity.EmployeeDTO;
import com.matoshri.employee.repo.EmployeeRepository;
import com.matoshri.employee.util.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository empRepo;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository empRepo, EmployeeMapper mapper) {
        this.empRepo = empRepo;
        this.mapper = mapper;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() throws ExecutionException, InterruptedException {
        List<EmployeeDTO> employeeDTOS;
        CompletableFuture<List<EmployeeDTO>> cf = CompletableFuture.supplyAsync(() -> {
            List<Employee> list = empRepo.findAll();
            return list.stream().map(mapper::mapToDTO).toList();
        });
        employeeDTOS = cf.get();
        return employeeDTOS;
    }

    @Override
    public Long saveEmployee(EmployeeDTO dto) {
        return null;
    }
}
