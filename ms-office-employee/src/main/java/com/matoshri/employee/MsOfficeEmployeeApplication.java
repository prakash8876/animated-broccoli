package com.matoshri.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class, HttpSecurity.class })
public class MsOfficeEmployeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsOfficeEmployeeApplication.class, args);
  }
}
