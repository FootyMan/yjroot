package com.myErp.enums;

/**
 * 用户级别
 * 
 * @author HCY
 *
 */
public enum UserLevel {

	Ordinary(1,"普通会员",1),
	Month(2,"包月会员",2),
	Half_a_year(3,"半年会员",3),
	Year(4,"半年会员",4);

	private int code;
	private int userLevel;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String desc;

	private UserLevel(int code, String desc, int userLevel) {
		this.code = code;
		this.desc = desc;
		this.userLevel = userLevel;
	}

	public static UserLevel getOrderStateByCode(int code) {
		for (UserLevel level : values()) {
			if (level.getCode() == code) {
				return level;
			}
		}
		return null;
	}

}
