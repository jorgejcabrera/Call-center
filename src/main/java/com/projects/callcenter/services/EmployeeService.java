package com.projects.callcenter.services;

import com.projects.callcenter.domain.Employee;
import com.projects.callcenter.domain.EmployeeRole;
import com.projects.callcenter.enums.Role;
import com.projects.callcenter.exception.NotFoundException;
import com.projects.callcenter.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public void create(Employee employee) {
        try{
            employeeRepository.save(employee);
        } catch (Exception ex){
            logger.error("It was an error while trying to create employee: {}",employee.toString());
            throw ex;
        }
    }

    public void assignRole(Long id, Role role) throws NotFoundException {
        Optional<Employee> employeeOp = employeeRepository.findById(id);
        if (!employeeOp.isPresent())
            throw new NotFoundException(String.format("Employee %s was not found.",id));
        List<Role> employeeRoles = employeeOp.get().getEmployeeRoles().stream().map(EmployeeRole::getRole).collect(Collectors.toList());
        Employee employee = employeeOp.get();
        if (!employeeRoles.contains(role)) {
            try {
                EmployeeRole employeeRole = new EmployeeRole();
                employeeRole.setRole(role);
                employeeRole.setEmployee(employee);
                employee.getEmployeeRoles().add(employeeRole);
                employeeRepository.save(employee);
            } catch (Exception ex) {
                logger.error("It was an error while trying to assign role:{}", employeeRoles.toString());
                throw ex;
            }
        }
    }

}
