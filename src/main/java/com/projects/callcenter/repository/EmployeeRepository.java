package com.projects.callcenter.repository;

import com.projects.callcenter.domain.Employee;
import com.projects.callcenter.enums.EmployeeStatus;
import com.projects.callcenter.enums.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    @Query(value = "SELECT e FROM Employee e " +
            "WHERE e.status = ?1 and e.id in " +
            "(SELECT er.id FROM EmployeeRole er WHERE er.role = ?2",nativeQuery = true)
    //@Query(value = "SELECT er.id FROM EmployeeRole er WHERE er.role = ?1")
    public List<Employee> findAllByStatusAndRole(EmployeeStatus status,Role role);
}
