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
 * RegisterEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_wechat_register"
    ,catalog="hfmobile"
)

public class RegisterEntity  implements java.io.Serializable {


    // Fields    

     private String id;
     private String openId;
     private String telephone;
     private Timestamp bindTime;
     private String recommendTelephone;

    // Constructors

    /** default constructor */
    public RegisterEntity() {
    }

    
    /** full constructor */
    public RegisterEntity(String openId, String telephone, Timestamp bindTime) {
        this.openId = openId;
        this.telephone = telephone;
        this.bindTime = bindTime;
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
    
    @Column(name="open_id", nullable=false, length=64)

    public String getOpenId() {
        return this.openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    @Column(name="telephone", nullable=false, length=11)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="bind_time", nullable=false, length=19)
    public Timestamp getBindTime() {
        return this.bindTime;
    }
    
    public void setBindTime(Timestamp bindTime) {
        this.bindTime = bindTime;
    }

    @Column(name="recommend_telephone", nullable=true, length=64)
	public String getRecommendTelephone() {
		return recommendTelephone;
	}
    
	public void setRecommendTelephone(String recommendTelephone) {
		this.recommendTelephone = recommendTelephone;
	}
}