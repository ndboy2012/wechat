package org.yelp.entity.seckill;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * SeckillPhoneInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_seckill_phoneinfo"
    ,catalog="wechat"
)

public class SeckillPhoneInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String phone;
     private Integer count;
     private Integer remains;
     private String describe;
     private Integer participateTime;
     private String headCode;
     private String imgUrl;


    // Constructors

    /** default constructor */
    public SeckillPhoneInfo() {
    }

    
    /** full constructor */
    public SeckillPhoneInfo(String phone, Integer count, Integer remains, String describe, Integer participateTime, String headCode, String imgUrl) {
        this.phone = phone;
        this.count = count;
        this.remains = remains;
        this.describe = describe;
        this.participateTime = participateTime;
        this.headCode = headCode;
        this.imgUrl = imgUrl;
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
    
    @Column(name="phone", nullable=false, length=64)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="count", nullable=false)

    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    @Column(name="remains", nullable=false)

    public Integer getRemains() {
        return this.remains;
    }
    
    public void setRemains(Integer remains) {
        this.remains = remains;
    }
    
    @Column(name="describe", nullable=false)

    public String getDescribe() {
        return this.describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    @Column(name="participateTime", nullable=false)

    public Integer getParticipateTime() {
        return this.participateTime;
    }
    
    public void setParticipateTime(Integer participateTime) {
        this.participateTime = participateTime;
    }
    
    @Column(name="headCode", nullable=false, length=4)

    public String getHeadCode() {
        return this.headCode;
    }
    
    public void setHeadCode(String headCode) {
        this.headCode = headCode;
    }
    
    @Column(name="imgUrl", nullable=false, length=64)

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
   








}