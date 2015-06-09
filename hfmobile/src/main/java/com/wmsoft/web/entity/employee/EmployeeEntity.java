package com.wmsoft.web.entity.employee;
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
 * EmployeeEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_hf_employee"
    ,catalog="hfmobile"
)

public class EmployeeEntity  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String sex;
     private String telephone;
     private String college;
     private String collegeRegion;
     private String department;
     private String major;
     private String level;
     private String association;
     private String carreer;
     private String personalIntroduct;
     private String prize;
     private Date applicateDate;

    // Constructors

    /** default constructor */
    public EmployeeEntity() {
    }

	/** minimal constructor */
    public EmployeeEntity(String name, String sex, String telephone, String college, String department, String major) {
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
        this.college = college;
        this.department = department;
        this.major = major;
    }
    
    /** full constructor */
    public EmployeeEntity(String name, String sex, String telephone, String college, String collegeRegion, String department, String major, String level, String association, String carreer, String personalIntroduct, String prize) {
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
        this.college = college;
        this.collegeRegion = collegeRegion;
        this.department = department;
        this.major = major;
        this.level = level;
        this.association = association;
        this.carreer = carreer;
        this.personalIntroduct = personalIntroduct;
        this.prize = prize;
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
    
    @Column(name="name", nullable=false, length=64)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="sex", nullable=false, length=64)

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="telephone", nullable=false, length=64)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="college", nullable=false, length=64)

    public String getCollege() {
        return this.college;
    }
    
    public void setCollege(String college) {
        this.college = college;
    }
    
    @Column(name="college_region", length=64)

    public String getCollegeRegion() {
        return this.collegeRegion;
    }
    
    public void setCollegeRegion(String collegeRegion) {
        this.collegeRegion = collegeRegion;
    }
    
    @Column(name="department", nullable=false, length=64)

    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    @Column(name="major", nullable=false, length=64)

    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    @Column(name="level", length=64)

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    @Column(name="association", length=64)

    public String getAssociation() {
        return this.association;
    }
    
    public void setAssociation(String association) {
        this.association = association;
    }
    
    @Column(name="carreer", length=64)

    public String getCarreer() {
        return this.carreer;
    }
    
    public void setCarreer(String carreer) {
        this.carreer = carreer;
    }
    
    @Column(name="personal_introduct", length=1024)

    public String getPersonalIntroduct() {
        return this.personalIntroduct;
    }
    
    public void setPersonalIntroduct(String personalIntroduct) {
        this.personalIntroduct = personalIntroduct;
    }
    
    @Column(name="prize", length=1024)

    public String getPrize() {
        return this.prize;
    }
    
    public void setPrize(String prize) {
        this.prize = prize;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="applicate_date", nullable=false, length=10)
	public Date getApplicateDate() {
		return applicateDate;
	}

	public void setApplicateDate(Date applicateDate) {
		this.applicateDate = applicateDate;
	}
}