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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DispatcherServiceTests {

    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    DispatcherService dispatcherService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_operatorIsAvailable_then_shouldReturnTrue(){
        //given
        List<Employee> employees = new ArrayList<>();
        Long id = 1L;
        Employee jorge = new EmployeeBuilder()
                .withId(id)
                .withLastName("Cabrera")
                .withName("Jorge")
                .withStatus(EmployeeStatus.FREE)
                .withEmployeeRoles( new HashSet<Role>() {{ add(Role.OPERATOR); }})
                .build();
        employees.add(jorge);

        //when
        when(employeeRepository.findAllByStatusAndRole(EmployeeStatus.FREE.ordinal(), Role.OPERATOR.ordinal())).thenReturn(employees);
        assertTrue(dispatcherService.dispatchCall().isPresent());
    }


    @Test
    public void when_allEmployeesIsNotAvailable_then_shouldReturnFalse(){
        //given
        List<Employee> employees = new ArrayList<>();
        Long id = 1L;
        Employee jorge = new EmployeeBuilder()
                .withId(id)
                .withLastName("Cabrera")
                .withName("Jorge")
                .withStatus(EmployeeStatus.BUSY)
                .withEmployeeRoles( new HashSet<Role>() {{ add(Role.OPERATOR); }})
                .build();
        employees.add(jorge);

        //when
        when(employeeRepository.findAllByStatusAndRole(EmployeeStatus.FREE.ordinal(), Role.OPERATOR.ordinal())).thenReturn(new ArrayList<>());
        assertFalse(dispatcherService.dispatchCall().isPresent());
    }
}
