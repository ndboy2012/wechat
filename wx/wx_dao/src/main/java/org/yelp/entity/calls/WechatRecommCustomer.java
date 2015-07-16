package org.yelp.entity.calls;
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
 * WechatRecommCustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_recommend_customer"
    ,catalog="wechat"
)

public class WechatRecommCustomer  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String recommendTel;
     private Date recommendDate;


    // Constructors

    /** default constructor */
    public WechatRecommCustomer() {
    }

    
    /** full constructor */
    public WechatRecommCustomer(String openId, String recommendTel, Date recommendDate) {
        this.openId = openId;
        this.recommendTel = recommendTel;
        this.recommendDate = recommendDate;
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
    
    @Column(name="recommend_tel", nullable=false, length=64)

    public String getRecommendTel() {
        return this.recommendTel;
    }
    
    public void setRecommendTel(String recommendTel) {
        this.recommendTel = recommendTel;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="recommend_date", nullable=false, length=10)

    public Date getRecommendDate() {
        return this.recommendDate;
    }
    
    public void setRecommendDate(Date recommendDate) {
        this.recommendDate = recommendDate;
    }
   








}