package com.projects.callcenter.domain;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="employee_role")
@IdClass(value = EmployeeRoleId.class)
public class EmployeeRole extends Auditable {

    @Id
    @Column
    EmployeeRole role;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns(value = {
            @JoinColumn(name="employee_id", nullable = false)},
            foreignKey = @ForeignKey(name = "employee_role_foreing_key"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    Employee employee;

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}