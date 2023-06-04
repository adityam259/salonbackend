package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "header_master")
public class UploadHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@JsonIgnore
	//private byte[] headerImage;
	private String headerImageDetailsId;
	private String headerName;
	private String title;
	private String time;
	private String rate;
	private String originalPrice;
	private String description;
	private byte[] image1;
	private byte[] image2;
	private byte[] image3;
	private byte[] image4;
	@Transient
	private String frontEndBase64;
	@Transient
	private String image1Base64;
	@Transient
	private String image2Base64;
	@Transient
	private String image3Base64;
	@Transient
	private String image4Base64;
	private String isDelete;
	private String membershipDiscount;
	private String membershipDiscount2;
	private String imageUploadDirectory;

	public String getImageUploadDirectory() {
		return imageUploadDirectory;
	}

	public void setImageUploadDirectory(String imageUploadDirectory) {
		this.imageUploadDirectory = imageUploadDirectory;
	}
	
	public String getMembershipDiscount() {
		return membershipDiscount;
	}

	public void setMembershipDiscount(String membershipDiscount) {
		this.membershipDiscount = membershipDiscount;
	}

	public String getMembershipDiscount2() {
		return membershipDiscount2;
	}

	public void setMembershipDiscount2(String membershipDiscount2) {
		this.membershipDiscount2 = membershipDiscount2;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getImage1Base64() {
		return image1Base64;
	}

	public void setImage1Base64(String image1Base64) {
		this.image1Base64 = image1Base64;
	}

	public String getImage2Base64() {
		return image2Base64;
	}

	public void setImage2Base64(String image2Base64) {
		this.image2Base64 = image2Base64;
	}

	public String getImage3Base64() {
		return image3Base64;
	}

	public void setImage3Base64(String image3Base64) {
		this.image3Base64 = image3Base64;
	}

	public String getImage4Base64() {
		return image4Base64;
	}

	public void setImage4Base64(String image4Base64) {
		this.image4Base64 = image4Base64;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public byte[] getImage4() {
		return image4;
	}

	public void setImage4(byte[] image4) {
		this.image4 = image4;
	}

	public String getFrontEndBase64() {
		return frontEndBase64;
	}

	public void setFrontEndBase64(String frontEndBase64) {
		this.frontEndBase64 = frontEndBase64;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeaderImageDetailsId() {
		return headerImageDetailsId;
	}

	public void setHeaderImageDetailsId(String headerImageDetailsId) {
		this.headerImageDetailsId = headerImageDetailsId;
	}

	
}
