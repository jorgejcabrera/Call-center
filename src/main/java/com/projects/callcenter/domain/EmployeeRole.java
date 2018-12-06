package com.projects.callcenter.domain;


import com.projects.callcenter.enums.Role;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_role")
@IdClass(value = EmployeeRoleId.class)
public class EmployeeRole extends Auditable {

    @Id
    @Column
    private Role role;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="employee_id",
            foreignKey = @ForeignKey(name = "employee_role_foreingn_key"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeRole{" +
                "role=" + role +
                ", employee=" + employee +
                '}';
    }
}