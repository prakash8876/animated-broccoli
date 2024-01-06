package com.matoshri.employee.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import java.io.Serializable;

@JsonInclude(NON_DEFAULT)
public class EmployeeDTO
    implements Serializable {

    private static final long serialVersionUID =
            234098243823485285L;

    private Long empId;
    private String empName;
    @Email(message = "Invalid email, please enter valid email address.")
    private  String empEmail;
    private Long depId;
    public EmployeeDTO() {

    }

    public EmployeeDTO(Long empId, String empName, String empEmail, Long depId) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.depId = depId;
    }

    public Long getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public Long getDepId() {
        return depId;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", depId=" + depId +
                '}';
    }
}
