package com.weimeng.entity.web.merry;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * MerryPrizeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_merry_prize_info"
    ,catalog="aqyd"
)

public class MerryPrizeInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String code;
     private String name;
     private Integer count;
     private Integer remain;


    // Constructors

    /** default constructor */
    public MerryPrizeInfo() {
    }

    
    /** full constructor */
    public MerryPrizeInfo(String code, String name, Integer count, Integer remain) {
        this.code = code;
        this.name = name;
        this.count = count;
        this.remain = remain;
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
    
    @Column(name="code", nullable=false, length=64)

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="name", nullable=false, length=64)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="count", nullable=false)

    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    @Column(name="remain", nullable=false)

    public Integer getRemain() {
        return this.remain;
    }
    
    public void setRemain(Integer remain) {
        this.remain = remain;
    }
   








}