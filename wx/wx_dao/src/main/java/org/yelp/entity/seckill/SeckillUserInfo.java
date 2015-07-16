package org.yelp.entity.seckill;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * SeckillUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_seckill_userinfo"
    ,catalog="wechat"
)

public class SeckillUserInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telephone;
     private String phone;
     private String code;
     private Timestamp date;


    // Constructors

    /** default constructor */
    public SeckillUserInfo() {
    }

    
    /** full constructor */
    public SeckillUserInfo(String openId, String telephone, String phone, String code, Timestamp date) {
        this.openId = openId;
        this.telephone = telephone;
        this.phone = phone;
        this.code = code;
        this.date = date;
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
    
    @Column(name="phone", nullable=false, length=64)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="code", nullable=false, length=64)

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="date", nullable=false, length=19)

    public Timestamp getDate() {
        return this.date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }
   








}