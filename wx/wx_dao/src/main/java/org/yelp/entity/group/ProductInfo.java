package org.yelp.entity.group;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ProductInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_cs_groupbuy_info"
    ,catalog="wechat"
)

public class ProductInfo  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String describe;
     private Integer nakePrice;
     private Integer groupPrice;
     private String picture;


    // Constructors

    /** default constructor */
    public ProductInfo() {
    }

    
    /** full constructor */
    public ProductInfo(String name, String describe, Integer nakePrice, Integer groupPrice, String picture) {
        this.name = name;
        this.describe = describe;
        this.nakePrice = nakePrice;
        this.groupPrice = groupPrice;
        this.picture = picture;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false, length=64)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="name", nullable=false, length=64)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="describe", nullable=false, length=64)

    public String getDescribe() {
        return this.describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    @Column(name="nake_price", nullable=false)

    public Integer getNakePrice() {
        return this.nakePrice;
    }
    
    public void setNakePrice(Integer nakePrice) {
        this.nakePrice = nakePrice;
    }
    
    @Column(name="group_price", nullable=false)

    public Integer getGroupPrice() {
        return this.groupPrice;
    }
    
    public void setGroupPrice(Integer groupPrice) {
        this.groupPrice = groupPrice;
    }
    
    @Column(name="picture", nullable=false, length=64)

    public String getPicture() {
        return this.picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
   








}