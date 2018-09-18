//package com.erp.utils;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "erp")
//@PropertySource("classpath:System.properties")
//public class PropertiesSystemConfig {
//
//	 
//	private String host;
//	private String port;
//	private String img_save_path;
//	private boolean isSign;
//	private boolean isEncrypt;
//	private double reward_percentage;
//	private int min_withdrawals;
//	private String envIdentity;
//	private boolean isread_redis;
//	private String api_address;
//	private String server_address;
//	private String img_prefix;
//	public String getHost() {
//		return host;
//	}
//	public void setHost(String host) {
//		this.host = host;
//	}
//	public String getPort() {
//		return port;
//	}
//	public void setPort(String port) {
//		this.port = port;
//	}
// 
//	public String getImg_save_path() {
//		return img_save_path;
//	}
//	public void setImg_save_path(String img_save_path) {
//		this.img_save_path = img_save_path;
//	}
//	public boolean isSign() {
//		return isSign;
//	}
//	public void setSign(boolean isSign) {
//		this.isSign = isSign;
//	}
//	public boolean isEncrypt() {
//		return isEncrypt;
//	}
//	public void setEncrypt(boolean isEncrypt) {
//		this.isEncrypt = isEncrypt;
//	}
//	public double getReward_percentage() {
//		return reward_percentage;
//	}
//	public void setReward_percentage(double reward_percentage) {
//		this.reward_percentage = reward_percentage;
//	}
//	public int getMin_withdrawals() {
//		return min_withdrawals;
//	}
//	public void setMin_withdrawals(int min_withdrawals) {
//		this.min_withdrawals = min_withdrawals;
//	}
//	public String getEnvIdentity() {
//		return envIdentity;
//	}
//	public void setEnvIdentity(String envIdentity) {
//		this.envIdentity = envIdentity;
//	}
//	public boolean isIsread_redis() {
//		return isread_redis;
//	}
//	public void setIsread_redis(boolean isread_redis) {
//		this.isread_redis = isread_redis;
//	}
//	public String getApi_address() {
//		return api_address;
//	}
//	public void setApi_address(String api_address) {
//		this.api_address = api_address;
//	}
//	public String getServer_address() {
//		return server_address;
//	}
//	public void setServer_address(String server_address) {
//		this.server_address = server_address;
//	}
//	public String getImg_prefix() {
//		return img_prefix;
//	}
//	public void setImg_prefix(String img_prefix) {
//		this.img_prefix = img_prefix;
//	}
//	
//	
//}
