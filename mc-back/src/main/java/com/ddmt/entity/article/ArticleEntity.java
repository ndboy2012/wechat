package com.ddmt.entity.article;


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
 * @Description: 文章内容表
 * @author yelp
 * @date 2015-01-04 14:39:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mctax_article_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ArticleEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	
	private java.lang.String belongId;
	/**父节点*/
	private java.lang.String parentId;
	/**活动内容*/
	private java.lang.String content;
	/**活动内容文本*/
	private java.lang.String contentTxt;
	
	private java.lang.String isTouch;
	
	private java.lang.String touchPath;
	
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
	 *@return: java.lang.String  父节点
	 */
	@Column(name ="PARENT_ID",nullable=false,length=64)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父节点
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动内容
	 */
	@Column(name ="CONTENT",nullable=true,length=16777215)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动内容文本
	 */
	@Column(name ="CONTENT_TXT",nullable=true,length=16777215)
	public java.lang.String getContentTxt(){
		return this.contentTxt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动内容文本
	 */
	public void setContentTxt(java.lang.String contentTxt){
		this.contentTxt = contentTxt;
	}
    
	@Column(name ="belongs_id",nullable=false,length=64)
	public java.lang.String getBelongId() {
		return belongId;
	}

	public void setBelongId(java.lang.String belongId) {
		this.belongId = belongId;
	}
    
	@Column(name ="is_touch",nullable=false,length=64)
	public java.lang.String getIsTouch() {
		return isTouch;
	}
    
	public void setIsTouch(java.lang.String isTouch) {
		this.isTouch = isTouch;
	}
    
	@Column(name ="touch_path",nullable=false,length=128)
	public java.lang.String getTouchPath() {
		return touchPath;
	}

	public void setTouchPath(java.lang.String touchPath) {
		this.touchPath = touchPath;
	}
	
}
