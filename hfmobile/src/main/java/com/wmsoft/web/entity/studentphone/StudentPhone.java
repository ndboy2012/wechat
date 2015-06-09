package com.wmsoft.web.entity.studentphone;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * AnonymousEntity entity
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_student_phone",catalog="hfmobile")
public class StudentPhone  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	 /**Id*/
    private Integer id;
    /**phone*/
    private String phone;
  
    @Id 
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
  
	public void setId(Integer id) {
        this.id = id;
    }
	
    @Column(name="phone", nullable=false, length=11)
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    
    
   
    
}