package com.ddmt.entity.dictionary;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 字典表
 * @author yelp
 * @date 2014-12-16 21:47:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mctax_dictionary", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DictionaryEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**categoryCode*/
	private java.lang.String categoryCode;
	/**dkey*/
	private java.lang.String dkey;
	/**name*/
	private java.lang.String name;
	/**value*/
	private java.lang.String value;
	/**status*/
	private java.lang.Integer status;
	/**description*/
	private java.lang.String description;
	/**createTime*/
	private java.util.Date createTime;
	/**updateTime*/
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
	 *@return: java.lang.String  categoryCode
	 */
	@Column(name ="CATEGORY_CODE",nullable=true,length=64)
	public java.lang.String getCategoryCode(){
		return this.categoryCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  categoryCode
	 */
	public void setCategoryCode(java.lang.String categoryCode){
		this.categoryCode = categoryCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  dkey
	 */
	@Column(name ="DKEY",nullable=true,length=64)
	public java.lang.String getDkey(){
		return this.dkey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  dkey
	 */
	public void setDkey(java.lang.String dkey){
		this.dkey = dkey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME",nullable=true,length=64)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  value
	 */
	@Column(name ="VALUE",nullable=true,length=64)
	public java.lang.String getValue(){
		return this.value;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  value
	 */
	public void setValue(java.lang.String value){
		this.value = value;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  status
	 */
	@Column(name ="STATUS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  status
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  description
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=256)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  description
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateTime
	 */
	@Column(name ="UPDATE_TIME",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateTime
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
