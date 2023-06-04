package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "membership_master")
public class MembershipMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String heading;
	private String description;
	private String termsConditions;
	private String price;
	private String isDelete;
	@Transient
	private String frontEndBase64;

	public MembershipMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MembershipMaster(Long id, String heading, String description, String termsConditions, String isDelte,
			String price) {
		super();
		this.id = id;
		this.heading = heading;
		this.description = description;
		this.termsConditions = termsConditions;
		this.isDelete = isDelte;
		this.price = price;
	}

	public MembershipMaster(String heading, String description, String termsConditions, String isDelte, String price) {
		super();
		this.heading = heading;
		this.description = description;
		this.termsConditions = termsConditions;
		this.isDelete = isDelte;
		this.price = price;
	}

	public String getFrontEndBase64() {
		return frontEndBase64;
	}

	public void setFrontEndBase64(String frontEndBase64) {
		this.frontEndBase64 = frontEndBase64;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

}
