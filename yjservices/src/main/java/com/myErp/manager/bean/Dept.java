package com.myErp.manager.bean;

import com.myErp.utils.Pagination;

public class Dept {

	private int deptId;
	private String deptName;
	private Pagination page;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	} 
}
