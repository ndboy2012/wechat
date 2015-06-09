package com.weimeng.entity.web.signInggk;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * WechatPrizeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_prize_info"
    ,catalog="aqyd"
)

public class WechatPrizeInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer prizeNo;
     private String prizeContent;
     private Integer prizeTotalCounts;
     private Integer prizeRemainCount;


    // Constructors

    /** default constructor */
    public WechatPrizeInfo() {
    }

    
    /** full constructor */
    public WechatPrizeInfo(Integer prizeNo, String prizeContent, Integer prizeTotalCounts, Integer prizeRemainCount) {
        this.prizeNo = prizeNo;
        this.prizeContent = prizeContent;
        this.prizeTotalCounts = prizeTotalCounts;
        this.prizeRemainCount = prizeRemainCount;
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
    
    @Column(name="prize_no", nullable=false)

    public Integer getPrizeNo() {
        return this.prizeNo;
    }
    
    public void setPrizeNo(Integer prizeNo) {
        this.prizeNo = prizeNo;
    }
    
    @Column(name="prize_content", nullable=false)

    public String getPrizeContent() {
        return this.prizeContent;
    }
    
    public void setPrizeContent(String prizeContent) {
        this.prizeContent = prizeContent;
    }
    
    @Column(name="prize_total_counts", nullable=false)

    public Integer getPrizeTotalCounts() {
        return this.prizeTotalCounts;
    }
    
    public void setPrizeTotalCounts(Integer prizeTotalCounts) {
        this.prizeTotalCounts = prizeTotalCounts;
    }
    
    @Column(name="prize_remain_count", nullable=false)

    public Integer getPrizeRemainCount() {
        return this.prizeRemainCount;
    }
    
    public void setPrizeRemainCount(Integer prizeRemainCount) {
        this.prizeRemainCount = prizeRemainCount;
    }
   








}