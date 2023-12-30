package com.matoshri.employee.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.matoshri.employee.entity.Employee;
import com.matoshri.employee.repo.EmployeeRepository;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class ApiConfig {

  @Value("${data.path}")
  private String dataPath;

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("EmployeeThread-");
    executor.initialize();
    return executor;
  }

  @Bean
  CommandLineRunner run(EmployeeRepository empRepo) {
    return args -> {
      Path path = Paths.get(dataPath + "mock-data.json");
      if ((empRepo.count() == 0L) && Files.exists(path)) {
        List<Employee> list = new Gson().fromJson(new JsonReader(new FileReader(path.toFile())),
                    TypeToken.getParameterized(ArrayList.class, Employee.class).getType());
        empRepo.saveAll(list);
      }
    };
  }
}
