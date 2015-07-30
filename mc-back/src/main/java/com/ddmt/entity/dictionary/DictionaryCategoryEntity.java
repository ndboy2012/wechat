package com.ddmt.entity.dictionary;

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
 * @Description: 字典类目表
 * @author zhangdaihao
 * @date 2014-12-16 21:49:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mctax_dictionary_category", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DictionaryCategoryEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**编号*/
	private java.lang.String code;
	/**名称*/
	private java.lang.String name;
	/**父结点*/
	private java.lang.String parentCode;
	/**是否是最后结点*/
	private java.lang.Integer isLastNode;
	/**节点级别*/
	private java.lang.Integer nodeLevel;
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
	 *@return: java.lang.String  编号
	 */
	@Column(name ="CODE",nullable=true,length=64)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	@Column(name ="NAME",nullable=true,length=64)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父结点
	 */
	@Column(name ="PARENT_CODE",nullable=true,length=64)
	public java.lang.String getParentCode(){
		return this.parentCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父结点
	 */
	public void setParentCode(java.lang.String parentCode){
		this.parentCode = parentCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否是最后结点
	 */
	@Column(name ="IS_LAST_NODE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getIsLastNode(){
		return this.isLastNode;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否是最后结点
	 */
	public void setIsLastNode(java.lang.Integer isLastNode){
		this.isLastNode = isLastNode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  节点级别
	 */
	@Column(name ="NODE_LEVEL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNodeLevel(){
		return this.nodeLevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  节点级别
	 */
	public void setNodeLevel(java.lang.Integer nodeLevel){
		this.nodeLevel = nodeLevel;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
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
	@Column(name ="UPDATE_TIME",nullable=true)
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
