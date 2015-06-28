package com.weimeng.entity.web.signInggk;
// default package

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
 * WechatSignInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_sign_info"
    ,catalog="aqyd"
)

public class WechatSignInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private Integer monthSignAccounts;
     private Date lastSignDate;


    // Constructors

    /** default constructor */
    public WechatSignInfo() {
    }

    
    /** full constructor */
    public WechatSignInfo(String openId, Integer monthSignAccounts, Date lastSignDate) {
        this.openId = openId;
        this.monthSignAccounts = monthSignAccounts;
        this.lastSignDate = lastSignDate;
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
    
    @Column(name="month_sign_accounts", nullable=false)

    public Integer getMonthSignAccounts() {
        return this.monthSignAccounts;
    }
    
    public void setMonthSignAccounts(Integer monthSignAccounts) {
        this.monthSignAccounts = monthSignAccounts;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="last_sign_date", nullable=false, length=10)

    public Date getLastSignDate() {
        return this.lastSignDate;
    }
    
    public void setLastSignDate(Date lastSignDate) {
        this.lastSignDate = lastSignDate;
    }
  
}