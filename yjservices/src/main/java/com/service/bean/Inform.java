package com.service.bean;

import java.util.Date;

public class Inform {

	private int informId;
	/**
	 * 举报人ID
	 */
	private int informUserId;
	/**
	 * 被举报人用户号
	 */
	private int beingInformId;
	/**
	 * 举报内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 原因
	 */
	private String reason; 
	public int getInformId() {
		return informId;
	}
	public void setInformId(int informId) {
		this.informId = informId;
	}
	public int getInformUserId() {
		return informUserId;
	}
	public void setInformUserId(int informUserId) {
		this.informUserId = informUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getBeingInformId() {
		return beingInformId;
	}
	public void setBeingInformId(int beingInformId) {
		this.beingInformId = beingInformId;
	}
	 
	
}
