package com.service.enums;

/**
 * 购买产品级别
 * 
 * @author HCY
 *
 */
public enum ProductLevel {

	// 产品ID---产品类型描述--类型ID---价格--整个产品描述
	LevleOne(1, "一个月", 2, 98.00, "¥98元 / 一个月", 30), LevleTwo(2, "一个月", 3, 298.00, "¥298元 / 半年", 180), LevleThree(3,
			"一个月", 4, 488.00, "¥488元 / 一年", 360);
	private int productId;
	private String typeDesc;
	private int typeId;
	private double price;
	private String productDesc;
	private int day;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	private ProductLevel(int productId, String typeDesc, int typeId, Double price, String productDesc, int day) {
		this.productId = productId;
		this.typeDesc = typeDesc;
		this.typeId = typeId;
		this.price = price;
		this.productDesc = productDesc;
		this.day = day;
	}

	public static ProductLevel getProductById(int productId) {
		for (ProductLevel level : values()) {
			if (level.getProductId() == productId) {
				return level;
			}
		}
		return null;
	}

}
