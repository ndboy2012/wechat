package com.ddmt.entity.leave;

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
 * @Description: 留言信息
 * @author yelp
 * @date 2015-01-17 16:44:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mctax_leave_message", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LeaveEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**性质*/
	private java.lang.String property;
	/**发留言人*/
	private java.lang.String sources;
	/**收留言人*/
	private java.lang.String target;
	/**content*/
	private java.lang.String content;
	/**是否处理*/
	private java.lang.String isHandle;
	/**createTime*/
	private java.util.Date createTime;
	
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
	 *@return: java.lang.String  性质
	 */
	@Column(name ="PROPERTY",nullable=false,length=64)
	public java.lang.String getProperty(){
		return this.property;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性质
	 */
	public void setProperty(java.lang.String property){
		this.property = property;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发留言人
	 */
	@Column(name ="SOURCES",nullable=false,length=64)
	public java.lang.String getSources(){
		return this.sources;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发留言人
	 */
	public void setSources(java.lang.String sources){
		this.sources = sources;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收留言人
	 */
	@Column(name ="TARGET",nullable=true,length=64)
	public java.lang.String getTarget(){
		return this.target;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收留言人
	 */
	public void setTarget(java.lang.String target){
		this.target = target;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  content
	 */
	@Column(name ="CONTENT",nullable=false,length=512)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  content
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否处理
	 */
	@Column(name ="IS_HANDLE",nullable=true,length=64)
	public java.lang.String getIsHandle(){
		return this.isHandle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否处理
	 */
	public void setIsHandle(java.lang.String isHandle){
		this.isHandle = isHandle;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME",nullable=false)
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
}
