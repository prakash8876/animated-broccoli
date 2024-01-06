package com.matoshri.msofficedepartment.exception;

import com.matoshri.msofficedepartment.constants.Constants;
import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1710960909213813729L;

  public EmployeeNotFoundException() {}

  public EmployeeNotFoundException(String message) {
    super(message);
  }

  public EmployeeNotFoundException(Long empId) {
    super(Constants.NOT_EXISTS + empId);
  }
}
