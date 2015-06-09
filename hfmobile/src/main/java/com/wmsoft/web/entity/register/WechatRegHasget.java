package com.wmsoft.web.entity.register;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * WechatRegHasget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_wechat_hasget"
    ,catalog="hfmobile"
)

public class WechatRegHasget  implements java.io.Serializable {


    // Fields    

     private String id;
     private String telephone;
     private Timestamp getTime;


    // Constructors

    /** default constructor */
    public WechatRegHasget() {
    }

    
    /** full constructor */
    public WechatRegHasget(String telephone, Timestamp getTime) {
        this.telephone = telephone;
        this.getTime = getTime;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false, length=64)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="telephone", nullable=false, length=11)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="get_time", nullable=false, length=19)

    public Timestamp getGetTime() {
        return this.getTime;
    }
    
    public void setGetTime(Timestamp getTime) {
        this.getTime = getTime;
    }
   








}