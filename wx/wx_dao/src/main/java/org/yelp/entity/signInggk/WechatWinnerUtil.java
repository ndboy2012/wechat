package org.yelp.entity.signInggk;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * WechatWinnerUtil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_wechat_winer_info_util"
    ,catalog="wechat"
)

public class WechatWinnerUtil  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String openId;
     private String telphone;
     private String prizeContent;


    // Constructors

    /** default constructor */
    public WechatWinnerUtil() {
    }

    
    /** full constructor */
    public WechatWinnerUtil(String openId, String telphone, String prizeContent) {
        this.openId = openId;
        this.telphone = telphone;
        this.prizeContent = prizeContent;
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
    
    @Column(name="telphone", nullable=true, length=11)

    public String getTelphone() {
        return this.telphone;
    }
    
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
    
    @Column(name="prize_content", nullable=false)

    public String getPrizeContent() {
        return this.prizeContent;
    }
    
    public void setPrizeContent(String prizeContent) {
        this.prizeContent = prizeContent;
    }
   








}