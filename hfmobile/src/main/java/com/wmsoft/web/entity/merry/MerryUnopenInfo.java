package com.wmsoft.web.entity.merry;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * MerryUnopenInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_merry_unopen_info"
    ,catalog="hfmobile"
)

public class MerryUnopenInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telephone;
     private Integer open;
     private Timestamp beiginTime;


    // Constructors

    /** default constructor */
    public MerryUnopenInfo() {
    }

    
    /** full constructor */
    public MerryUnopenInfo(String openId, String telephone, Integer open, Timestamp beiginTime) {
        this.openId = openId;
        this.telephone = telephone;
        this.open = open;
        this.beiginTime = beiginTime;
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
    
    @Column(name="open", nullable=false)

    public Integer getOpen() {
        return this.open;
    }
    
    public void setOpen(Integer open) {
        this.open = open;
    }
    
    @Column(name="beigin_time", nullable=false, length=19)

    public Timestamp getBeiginTime() {
        return this.beiginTime;
    }
    
    public void setBeiginTime(Timestamp beiginTime) {
        this.beiginTime = beiginTime;
    }
   








}