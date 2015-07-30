package com.ddmt.bean;

import java.util.List;

public class ItemVo {

	/** id */
	private java.lang.Integer id;
	/** 类型 */
	private java.lang.String title;

	private List<ItemVo> children;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public List<ItemVo> getChildren() {
		return children;
	}

	public void setChildren(List<ItemVo> children) {
		this.children = children;
	}

}
