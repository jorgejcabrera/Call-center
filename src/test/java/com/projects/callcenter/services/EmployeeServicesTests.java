package com.projects.callcenter.services;

import com.projects.callcenter.builder.EmployeeBuilder;
import com.projects.callcenter.domain.Employee;
import com.projects.callcenter.enums.EmployeeStatus;
import com.projects.callcenter.enums.Role;
import com.projects.callcenter.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServicesTests {

    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void when_EmployeeDoesNotExists_shouldReturnBadRequest() {
        //given
        Long id = 1L;
        //when
        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        try {
            employeeService.assignRole(id, Role.SUPERVISOR);
        } catch (Exception ex) {
            assertEquals("Employee 1 was not found.",ex.getMessage());
        }
    }


    @Test
    public void when_assignSupervisorRoleForOperatorEmployee_shouldWorkOk() {
        //given
        Long id = 1L;
        Employee jorge = new EmployeeBuilder()
                .withId(id)
                .withLastName("Cabrera")
                .withName("Jorge")
                .withStatus(EmployeeStatus.FREE)
                .withEmployeeRoles( new HashSet<Role>() {{ add(Role.OPERATOR); }})
                .build();

        //when
        when(employeeRepository.findById(id)).thenReturn(Optional.of(jorge));
        try {
            employeeService.assignRole(id, Role.SUPERVISOR);
        } catch (Exception ex) {
            fail("TEST FAIL " + ex.getCause());
        }
    }

    @Test
    public void when_assignNewOperatorRole_shouldWorkOk() {
        //given
        Long id = 1L;
        Employee jorge = new EmployeeBuilder()
                .withId(id)
                .withLastName("Cabrera")
                .withName("Jorge")
                .withStatus(EmployeeStatus.FREE)
                .withEmployeeRoles( new HashSet<>())
                .build();

        //when
        when(employeeRepository.findById(id)).thenReturn(Optional.of(jorge));
        try {
            employeeService.assignRole(id, Role.OPERATOR);
        } catch (Exception ex) {
            fail("TEST FAIL " + ex.getCause());
        }
    }

    @Test
    public void when_assignOperatorRoleToOperatorEmployee_shouldIgnoreNewRole() {
        //given
        Long id = 1L;
        Employee jorge = new EmployeeBuilder()
                .withId(id)
                .withLastName("Cabrera")
                .withName("Jorge")
                .withStatus(EmployeeStatus.FREE)
                .withEmployeeRoles( new HashSet<Role>() {{ add(Role.OPERATOR); }})
                .build();

        //when
        when(employeeRepository.findById(id)).thenReturn(Optional.of(jorge));
        try {
            employeeService.assignRole(id, Role.OPERATOR);
        } catch (Exception ex) {
            fail("TEST FAIL " + ex.getCause());
        }
    }
}
