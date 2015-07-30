package com.ddmt.entity.district;

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
 * @Description: 地区基本信息
 * @author yelp
 * @date 2015-01-16 13:42:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mctax_district_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DistrictEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**地区编号*/
	private java.lang.String districtCode;
	/**地区名称*/
	private java.lang.String districtName;
	/**email*/
	private java.lang.String email;
	/**电话*/
	private java.lang.String telephone;
	/**详细地址*/
	private java.lang.String address;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=64)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区编号
	 */
	@Column(name ="DISTRICT_CODE",nullable=false,length=64)
	public java.lang.String getDistrictCode(){
		return this.districtCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区编号
	 */
	public void setDistrictCode(java.lang.String districtCode){
		this.districtCode = districtCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区名称
	 */
	@Column(name ="DISTRICT_NAME",nullable=false,length=64)
	public java.lang.String getDistrictName(){
		return this.districtName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区名称
	 */
	public void setDistrictName(java.lang.String districtName){
		this.districtName = districtName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  email
	 */
	@Column(name ="EMAIL",nullable=true,length=64)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  email
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="TELEPHONE",nullable=true,length=64)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详细地址
	 */
	@Column(name ="ADDRESS",nullable=false,length=128)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详细地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=false)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="UPDATE_TIME",nullable=false)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
