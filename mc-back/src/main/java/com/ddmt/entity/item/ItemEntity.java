package com.ddmt.entity.item;

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

import com.ddmt.entity.article.ArticleEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 类目基本信息
 * @author yelp
 * @date 2015-01-16 19:40:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mctax_item_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ItemEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**所属地区*/
	private java.lang.String belongs;
	/**创建者*/
	private java.lang.String creator;
	/**是否公有*/
	private java.lang.String isPublic;
	/**显示标题*/
	private java.lang.String title;
	/**内容编号*/
	private ArticleEntity article;
	/**类型*/
	private java.lang.String type;
	/**状态*/
	private java.lang.String status;
	/**parentId*/
	private java.lang.String parentId;
	/**等级*/
	private java.lang.Integer level;
	/**排序*/
	private java.lang.Integer sort;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
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
	 *@return: java.lang.String  所属地区
	 */
	@Column(name ="BELONGS",nullable=false,length=64)
	public java.lang.String getBelongs(){
		return this.belongs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属地区
	 */
	public void setBelongs(java.lang.String belongs){
		this.belongs = belongs;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建者
	 */
	@Column(name ="CREATOR",nullable=false,length=64)
	public java.lang.String getCreator(){
		return this.creator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建者
	 */
	public void setCreator(java.lang.String creator){
		this.creator = creator;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否公有
	 */
	@Column(name ="IS_PUBLIC",nullable=false,length=64)
	public java.lang.String getIsPublic(){
		return this.isPublic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否公有
	 */
	public void setIsPublic(java.lang.String isPublic){
		this.isPublic = isPublic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  显示标题
	 */
	@Column(name ="TITLE",nullable=false,length=512)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  显示标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内容编号
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "article", updatable = true, insertable = true, referencedColumnName = "id")
	public ArticleEntity getArticle() {
		return article;
	}

	public void setArticle(ArticleEntity article) {
		this.article = article;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */
	@Column(name ="TYPE",nullable=false,length=64)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=false,length=64)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  parentId
	 */
	@Column(name ="PARENT_ID",nullable=false,length=64)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  parentId
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  等级
	 */
	@Column(name ="LEVEL",nullable=false,precision=10,scale=0)
	public java.lang.Integer getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  等级
	 */
	public void setLevel(java.lang.Integer level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序
	 */
	@Column(name ="SORT",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSort(){
		return this.sort;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序
	 */
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
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
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="UPDATE_TIME",nullable=false)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
