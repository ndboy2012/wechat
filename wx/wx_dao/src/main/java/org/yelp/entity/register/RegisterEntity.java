package org.yelp.entity.register;

// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Register entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_cs_wechat_register", catalog = "wechat")
public class RegisterEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String openId;
	private String telephone;
	private String recommender;
	private Timestamp registerTime;

	// Constructors

	/** default constructor */
	public RegisterEntity() {
	}

	/** full constructor */
	public RegisterEntity(String openId, String telephone,
			Timestamp registerTime) {
		this.openId = openId;
		this.telephone = telephone;
		this.registerTime = registerTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "open_id", nullable = false, length = 64)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "telephone", nullable = false, length = 64)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "register_time", nullable = false, length = 19)
	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "recommender", nullable = false, length = 11)
	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

}