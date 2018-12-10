package com.projects.callcenter.services;

import com.projects.callcenter.domain.Employee;
import com.projects.callcenter.enums.EmployeeStatus;
import com.projects.callcenter.enums.Role;
import com.projects.callcenter.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispatcherService {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> assignCall() {
        Optional<Employee> operator = assignCallByRole(Role.OPERATOR);
        if (operator.isPresent())
            return operator;
        Optional<Employee> supervisor = assignCallByRole(Role.SUPERVISOR);
        if (supervisor.isPresent())
            return supervisor;
        return assignCallByRole(Role.DIRECTOR);
    }

    private Optional<Employee> assignCallByRole(Role role) {
        try{
            List<Employee> freeEmployees = employeeRepository.findAllByStatusAndRole(EmployeeStatus.FREE.ordinal(), role.ordinal());
            if (!freeEmployees.isEmpty()) {
                Employee employee = freeEmployees.iterator().next();
                employee.setStatus(EmployeeStatus.BUSY);
                employeeRepository.save(employee);
                logger.info("Employee {} is taking a call.",String.format("id %s %s %s",employee.getId(),employee.getLastName(),employee.getName()));
                return Optional.ofNullable(employee);
            }
        } catch (Exception ex) {
            logger.error("It was an error while trying to assign call: {}",ex.getMessage());
            throw ex;
        }
        return Optional.empty();
    }
}
