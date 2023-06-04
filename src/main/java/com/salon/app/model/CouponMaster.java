package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "coupon_master")
public class CouponMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String couponCode;
	private String description;
	private String isDelete;
	@Transient
	private String frontEndBase64;
	private String imageUploadDirectory;
	private String imageName;
	

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageUploadDirectory() {
		return imageUploadDirectory;
	}

	public void setImageUploadDirectory(String imageUploadDirectory) {
		this.imageUploadDirectory = imageUploadDirectory;
	}

	public String getFrontEndBase64() {
		return frontEndBase64;
	}

	public void setFrontEndBase64(String frontEndBase64) {
		this.frontEndBase64 = frontEndBase64;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

}
