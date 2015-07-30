package com.ddmt.entity.commonadmin;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.ddmt.entity.district.DistrictEntity;

import javax.persistence.SequenceGenerator;

/**
 * @Title: Entity
 * @Description: 二级管理员基本信息
 * @author yelp
 * @date 2015-01-16 13:34:36
 * @version V1.0
 * 
 */
@Entity
@Table(name = "tb_mctax_common_admin_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CommonAdminEntity implements java.io.Serializable {
	/** id */
	private java.lang.String id;
	/** 用户名 */
	private java.lang.String username;
	/** 所属地区 */
	private DistrictEntity district;
	/** 角色 */
	private java.lang.String role;
	/** 描述 */
	private java.lang.String content;
	private java.lang.String telephone;
	private java.lang.String name;
	/** 状态 */
	private java.lang.String status;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新时间 */
	private java.util.Date updateTime;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String id
	 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 64)
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 64)
	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 角色
	 */
	@Column(name = "ROLE", nullable = false, length = 64)
	public java.lang.String getRole() {
		return this.role;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 角色
	 */
	public void setRole(java.lang.String role) {
		this.role = role;
	}

	@Column(name = "CONTENT", nullable = false, length = 64)
	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 状态
	 */
	@Column(name = "STATUS", nullable = false, length = 64)
	public java.lang.String getStatus() {
		return this.status;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 状态
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	@Column(name = "CREATE_TIME", nullable = false)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新时间
	 */
	@Column(name = "UPDATE_TIME", nullable = false)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district", updatable = true, insertable = true, referencedColumnName = "district_code")
	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
    
	@Column(name = "telephone", nullable = true)
	public java.lang.String getTelephone() {
		return telephone;
	}
    
	
	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
	}
    
	@Column(name = "name", nullable = true)
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

}
