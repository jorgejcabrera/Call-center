package com.projects.callcenter.builder;

import com.projects.callcenter.domain.Employee;
import com.projects.callcenter.domain.EmployeeRole;
import com.projects.callcenter.enums.EmployeeStatus;
import com.projects.callcenter.enums.Role;
import java.util.HashSet;
import java.util.Set;

public class EmployeeBuilder {

    private Long id;
    private String name;
    private String lastName;
    private EmployeeStatus status;
    private Set<Role> employeeRoles;

    public EmployeeBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder withStatus(EmployeeStatus status) {
        this.status = status;
        return this;
    }

    public EmployeeBuilder withEmployeeRoles(Set<Role> employeeRoles) {
        this.employeeRoles = employeeRoles;
        return this;
    }

    public Employee build() {
        Employee employee = new Employee();
        employee.setId(this.id);
        employee.setName(this.name);
        employee.setLastName(this.lastName);
        employee.setStatus(this.status);

        Set<EmployeeRole> empRoles = new HashSet<>();
        this.employeeRoles.forEach( employeeRole -> {
            EmployeeRole empRole = new EmployeeRole();
            empRole.setEmployee(employee);
            empRole.setRole(employeeRole);
            empRoles.add(empRole);
        });
        employee.setEmployeeRoles(empRoles);
        return employee;
    }
}
