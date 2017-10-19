package com.service.bean;

/**
 * 用户标签映射表
 * 
 * @author HCY
 *
 */
public class UserLableMapping {

	/**
	 * 主键ID
	 */
	private int mappingId;
	private int userId;
	/**
	 * 标签ID
	 */
	private int lableId;

	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLableId() {
		return lableId;
	}

	public void setLableId(int lableId) {
		this.lableId = lableId;
	}

	public int getLableType() {
		return lableType;
	}

	public void setLableType(int lableType) {
		this.lableType = lableType;
	}

	/**
	 * 标签类型 1个性类 2运动类 3音乐类 4美食类 5旅游类
	 */
	private int lableType;
	/**
	 * 标签库
	 */
	private LabletType labletTypes;

	public LabletType getLabletTypes() {
		return labletTypes;
	}

	public void setLabletTypes(LabletType labletTypes) {
		this.labletTypes = labletTypes;
	}
}
