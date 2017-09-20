package com.myErp.manager.bean;

/**
 * 用户体型表
 * @author HCY
 *
 */
public class UserShape {

	private int shapeId;
	private String shapeName;
	private int sort;
	public int getShapeId() {
		return shapeId;
	}
	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}
	public String getShapeName() {
		return shapeName;
	}
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
