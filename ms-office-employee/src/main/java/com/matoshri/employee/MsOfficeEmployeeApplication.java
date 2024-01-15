package com.matoshri.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class, HttpSecurity.class })
public class MsOfficeEmployeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsOfficeEmployeeApplication.class, args);
  }
}
