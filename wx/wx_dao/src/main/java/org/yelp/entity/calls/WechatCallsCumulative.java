package org.yelp.entity.calls;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * WechatCallsCumulative entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_calls_cumulative"
    ,catalog="wechat"
)

public class WechatCallsCumulative  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telephone;
     private Integer ammounts;


    // Constructors

    /** default constructor */
    public WechatCallsCumulative() {
    }

    
    /** full constructor */
    public WechatCallsCumulative(String openId, String telephone, Integer ammounts) {
        this.openId = openId;
        this.telephone = telephone;
        this.ammounts = ammounts;
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
    
    @Column(name="telephone", nullable=false, length=64)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="ammounts", nullable=false)

    public Integer getAmmounts() {
        return this.ammounts;
    }
    
    public void setAmmounts(Integer ammounts) {
        this.ammounts = ammounts;
    }
   








}