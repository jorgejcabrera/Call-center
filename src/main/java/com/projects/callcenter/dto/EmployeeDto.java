package com.projects.callcenter.dto;

import com.projects.callcenter.enums.EmployeeStatus;
import com.projects.callcenter.enums.Role;

import java.util.Set;

public class EmployeeDto {

    private String name;
    private String lastName;
    private EmployeeStatus status;
    private Set<Role> employeeRoles;

    public EmployeeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public Set<Role> getEmployeeRoles() {
        return employeeRoles;
    }

    public void setEmployeeRoles(Set<Role> employeeRoles) {
        this.employeeRoles = employeeRoles;
    }
}
