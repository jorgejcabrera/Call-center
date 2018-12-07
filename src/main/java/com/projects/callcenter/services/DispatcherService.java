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

@Service
public class DispatcherService {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean assignCall() {
        return (assignCallByRole(Role.OPERATOR) ||
                assignCallByRole(Role.SUPERVISOR) ||
                assignCallByRole(Role.DIRECTOR));
    }

    private boolean assignCallByRole(Role role) {
        try{
            List<Employee> freeEmployees = employeeRepository.findAllByStatusAndRole(EmployeeStatus.FREE.ordinal(), role.ordinal());
            if (!freeEmployees.isEmpty()) {
                Employee employee = freeEmployees.iterator().next();
                employee.setStatus(EmployeeStatus.BUSY);
                employeeRepository.save(employee);
                logger.info("Employee {} is taking a call.",String.format("id %s %s %s",employee.getId(),employee.getLastName(),employee.getName()));
                return true;
            }
        } catch (Exception ex) {
            logger.error("It was an error while trying to assign call: {}",ex.getMessage());
            throw ex;
        }
        return false;
    }
}
