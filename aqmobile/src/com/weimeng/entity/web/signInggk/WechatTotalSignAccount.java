package com.weimeng.entity.web.signInggk;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * WechatTotalSignAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_sign_total_accounts_util"
    ,catalog="aqyd"
)

public class WechatTotalSignAccount  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telephone;
     private Integer monthSignAccounts;


    // Constructors

    /** default constructor */
    public WechatTotalSignAccount() {
    }

    
    /** full constructor */
    public WechatTotalSignAccount(String openId, String telephone, Integer monthSignAccounts) {
        this.openId = openId;
        this.telephone = telephone;
        this.monthSignAccounts = monthSignAccounts;
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
    
    @Column(name="month_sign_accounts", nullable=false)

    public Integer getMonthSignAccounts() {
        return this.monthSignAccounts;
    }
    
    public void setMonthSignAccounts(Integer monthSignAccounts) {
        this.monthSignAccounts = monthSignAccounts;
    }
}