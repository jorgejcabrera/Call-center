package com.projects.callcenter.controller;

import com.projects.callcenter.dto.EmployeeDto;
import com.projects.callcenter.enums.Role;
import com.projects.callcenter.exception.NotFoundException;
import com.projects.callcenter.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/callcenter/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity hire(@RequestBody EmployeeDto employeeDto) {
        employeeService.create(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "{employeeId}/role/{role}",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity assignRole(@PathVariable Long employeeId, @PathVariable Role role) throws NotFoundException {
        employeeService.assignRole(employeeId,role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{employeeId}/free",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity freeEmployee(@PathVariable Long employeeId) throws NotFoundException  {
        employeeService.freeEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
