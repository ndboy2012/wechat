package com.weimeng.entity.web.Admin;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * AdminBaseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_admin_base_info"
    ,catalog="aqyd"
)

public class AdminBaseInfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String username;
     private String password;
     private Timestamp lastLoginTime;


    // Constructors

    /** default constructor */
    public AdminBaseInfo() {
    }

    
    /** full constructor */
    public AdminBaseInfo(String username, String password, Timestamp lastLoginTime) {
        this.username = username;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
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
    
    @Column(name="username", nullable=false, length=64)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="password", nullable=false, length=64)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="last_login_time", nullable=false, length=19)

    public Timestamp getLastLoginTime() {
        return this.lastLoginTime;
    }
    
    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
   








}