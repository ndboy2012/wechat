package com.wmsoft.web.entity.anonymous;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * AnonymousEntity entity
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_anonymous",catalog="hfmobile")
public class AnonymousEntity  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**id*/
    private Integer id;
    /**openId*/
    private String openId;
    /**姓名*/
    private String name;
    /**性别*/
    private String sex;
    /**电话号码*/
    private String telephone;
    /**学校*/
    private String college;
    /**院系*/
    private String department;
    /**年级*/
    private String grade;
	/**选择*/
    private String reward;
    /**提交时间*/
    private Date createDate;

 
   
    @Id 
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="open_id", nullable=false, length=64)
    public String getOpenId() {
        return this.openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    @Column(name="name", nullable=false, length=64)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="sex", nullable=false, length=64)
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="telephone", nullable=false, length=64)
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="college", nullable=false, length=64)
    public String getCollege() {
        return this.college;
    }
    
    public void setCollege(String college) {
        this.college = college;
    }
   
    @Column(name="department", nullable=false, length=64)
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    @Column(name="grade", nullable=false, length=64)
    public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
    
    @Column(name="reward", length=64)
    public String getReward() {
        return this.reward;
    }
    
    public void setReward(String reward) {
        this.reward = reward;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="create_date", nullable=false, length=10)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}