package com.projects.callcenter.repository;

import com.projects.callcenter.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    @Query(value = "select * from Employee e where e.status = ?1 and e.id in (SELECT er.employee_id FROM Employee_role er WHERE er.role = ?2)", nativeQuery = true)
    public List<Employee> findAllByStatusAndRole(int status,int role);
}
