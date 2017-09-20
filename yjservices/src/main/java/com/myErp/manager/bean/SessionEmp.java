package com.myErp.manager.bean;

public class SessionEmp
{
  private Employee employee;
  private Role role;

  public SessionEmp(Employee user, Role role)
  {
    this.employee = user;
    this.role = role;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}