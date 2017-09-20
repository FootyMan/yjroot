package com.myErp.manager.bean;

import java.io.Serializable;
import java.util.Date;

import com.myErp.utils.Pagination;

public class Role implements Serializable {
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	private Integer roleId;
	private String roleName;
	private Date createDate;
	private Integer depId;
	private Pagination page;
}
