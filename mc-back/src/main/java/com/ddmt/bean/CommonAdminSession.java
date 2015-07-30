package com.ddmt.bean;

import com.ddmt.entity.district.DistrictEntity;

/**
 * 普通管理登入后存放的session
 * 
 * @author 叶兰平
 * 
 */
public class CommonAdminSession {

	private String id;
	private String username;
	private String role;
	private DistrictEntity district;
	private boolean isFirst;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

}
