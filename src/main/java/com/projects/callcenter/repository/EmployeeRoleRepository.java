package com.projects.callcenter.repository;

import com.projects.callcenter.domain.EmployeeRole;
import com.projects.callcenter.domain.EmployeeRoleId;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, EmployeeRoleId> {
}
