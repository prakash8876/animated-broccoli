package com.matoshri.msofficedepartment.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.matoshri.msofficedepartment.entity.Department;
import com.matoshri.msofficedepartment.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@EnableTransactionManagement
@EnableDiscoveryClient
public class ApiConfig {
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("DepartmentThread-");
        executor.initialize();
        return executor;
    }

    @Bean
    CommandLineRunner runner(DepartmentRepository repo) {
        return args -> {
            Path path = Paths.get("");
            if ((repo.count() == 0L) && Files.exists(path)) {
                ArrayList<Department> list = new Gson().fromJson(new JsonReader(new FileReader(path.toFile())),
                        TypeToken.getParameterized(ArrayList.class, Department.class).getType());
                repo.saveAll(list);
            }
        };
    }
}
