package com.wmsoft.web.entity.register;


// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * Recommnder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_register_recommender"
    ,catalog="hfmobile"
)

public class Recommnder  implements java.io.Serializable {


    // Fields    

     private String id;
     private String recommendTelephone;
     private Integer recommendCount;


    // Constructors

    /** default constructor */
    public Recommnder() {
    }

    
    /** full constructor */
    public Recommnder(String recommendTelephone, Integer recommendCount) {
        this.recommendTelephone = recommendTelephone;
        this.recommendCount = recommendCount;
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
    
    @Column(name="recommend_telephone", nullable=false, length=64)

    public String getRecommendTelephone() {
        return this.recommendTelephone;
    }
    
    public void setRecommendTelephone(String recommendTelephone) {
        this.recommendTelephone = recommendTelephone;
    }
    
    @Column(name="recommend_count", nullable=false)

    public Integer getRecommendCount() {
        return this.recommendCount;
    }
    
    public void setRecommendCount(Integer recommendCount) {
        this.recommendCount = recommendCount;
    }
   








}