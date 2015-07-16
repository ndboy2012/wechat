package org.yelp.entity.signInggk;
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
 * WechatWinPrizes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_win_prizes"
    ,catalog="wechat"
)

public class WechatWinPrizes  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private Integer prizeNo;
     private Date winDate;


    // Constructors

    /** default constructor */
    public WechatWinPrizes() {
    }

    
    /** full constructor */
    public WechatWinPrizes(String openId, Integer prizeNo, Date winDate) {
        this.openId = openId;
        this.prizeNo = prizeNo;
        this.winDate = winDate;
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
    
    @Column(name="prize_no", nullable=false)

    public Integer getPrizeNo() {
        return this.prizeNo;
    }
    
    public void setPrizeNo(Integer prizeNo) {
        this.prizeNo = prizeNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="win_date", nullable=false, length=10)

    public Date getWinDate() {
        return this.winDate;
    }
    
    public void setWinDate(Date winDate) {
        this.winDate = winDate;
    }
   








}