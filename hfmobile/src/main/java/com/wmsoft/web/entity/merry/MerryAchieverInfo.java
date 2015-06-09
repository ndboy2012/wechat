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
 * MerryAchieverInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_merry_achiever_info"
    ,catalog="hfmobile"
)

public class MerryAchieverInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telephone;
     private String prizeName;
     private Timestamp achieveTime;


    // Constructors

    /** default constructor */
    public MerryAchieverInfo() {
    }

    
    /** full constructor */
    public MerryAchieverInfo(String openId, String telephone, String prizeName, Timestamp achieveTime) {
        this.openId = openId;
        this.telephone = telephone;
        this.prizeName = prizeName;
        this.achieveTime = achieveTime;
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
    
    @Column(name="prize_name", nullable=false, length=64)

    public String getPrizeName() {
        return this.prizeName;
    }
    
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }
    
    @Column(name="achieve_time", nullable=false, length=19)

    public Timestamp getAchieveTime() {
        return this.achieveTime;
    }
    
    public void setAchieveTime(Timestamp achieveTime) {
        this.achieveTime = achieveTime;
    }
   
}