package com.ddmt.entity.user;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**
 * @Title: Entity
 * @Description: 用户登入信息
 * @author yelp
 * @date 2015-01-17 20:50:23
 * @version V1.0
 * 
 */
@Entity
@Table(name = "tb_mctax_user_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class UserEntity implements java.io.Serializable {
	/** id */
	private java.lang.String id;
	/** 登入名 */
	private java.lang.String username;
	/** 登入名 */
	private java.lang.String userId;
	/** 密码 */
	private java.lang.String password;
	/** role */
	private java.lang.String role;
	/** role */
	private java.lang.String status;

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

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 登入名
	 */
	@Column(name = "USERNAME", nullable = false, length = 64)
	public java.lang.String getUsername() {
		return this.username;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 登入名
	 */
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	@Column(name = "USER_ID", nullable = false, length = 64)
	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 密码
	 */
	@Column(name = "PASSWORD", nullable = false, length = 64)
	public java.lang.String getPassword() {
		return this.password;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 密码
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String role
	 */
	@Column(name = "ROLE", nullable = false, length = 64)
	public java.lang.String getRole() {
		return this.role;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String role
	 */
	public void setRole(java.lang.String role) {
		this.role = role;
	}

	@Column(name = "status", nullable = false, length = 64)
	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

}
