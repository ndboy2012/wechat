package com.weimeng.entity.web.register;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * WechatBaseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wxinfo"
    ,catalog="aqyd"
)

public class WechatBaseInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telephone;
     private Timestamp bindDate;


    // Constructors

    /** default constructor */
    public WechatBaseInfo() {
    }

    
    /** full constructor */
    public WechatBaseInfo(String openId, String telephone, Timestamp bindDate) {
        this.openId = openId;
        this.telephone = telephone;
        this.bindDate = bindDate;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
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
    
    @Column(name="telephone", nullable=false, length=11)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="bind_date", nullable=false, length=19)

    public Timestamp getBindDate() {
        return this.bindDate;
    }
    
    public void setBindDate(Timestamp bindDate) {
        this.bindDate = bindDate;
    }
   








}