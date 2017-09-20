package com.myErp.manager.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.myErp.utils.Pagination;

public class Employee implements Serializable {
	private Integer id;
	private String empNo;
	private String empName;
	private String empLogin;
	private String empEmail;
	private String empPwd;
	private String empGender;
	private String empPhone;
	private String empQq;
	private String empWx;
	private Integer roleId;
	private String cityCode;
	private String deptCode;
	private String empStatus;
	private Date addDate;
	private Pagination page;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLogin() {
		return empLogin;
	}

	public void setEmpLogin(String empLogin) {
		this.empLogin = empLogin;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpQq() {
		return empQq = (empQq == null ? "" : empQq.trim());
	}

	public void setEmpQq(String empQq) {
		this.empQq = (empQq == null ? "" : empQq.trim());
	}

	public String getEmpWx() {
		return empWx;
	}

	public void setEmpWx(String empWx) {
		this.empWx = (empWx == null ? "" : empWx.trim());
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = (roleId == null ? 0 : roleId);
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = (roleId == null ? "" : cityCode);
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date quitDate;
}
