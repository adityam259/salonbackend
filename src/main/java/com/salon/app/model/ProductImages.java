package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product_master")
public class ProductImages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private byte[] productImage;
	private String imageUploadDirectory;
	private String imageName;
	private String productImageDetailsId;
	private String productName;
	private String originalPrice;
	private String title;
	private String time;
	private String rate;
	private String description;
	//private byte[] image1;
	//private byte[] image2;
	//private byte[] image3;
	//private byte[] image4;
	@Transient
	private String productFrontEndBase64;
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

	public String getFrontEndBase64() {
		return frontEndBase64;
	}

	public void setFrontEndBase64(String frontEndBase64) {
		this.frontEndBase64 = frontEndBase64;
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

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getImageUploadDirectory() {
		return imageUploadDirectory;
	}

	public void setImageUploadDirectory(String imageUploadDirectory) {
		this.imageUploadDirectory = imageUploadDirectory;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getProductImageDetailsId() {
		return productImageDetailsId;
	}

	public void setProductImageDetailsId(String productImageDetailsId) {
		this.productImageDetailsId = productImageDetailsId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductFrontEndBase64() {
		return productFrontEndBase64;
	}

	public void setProductFrontEndBase64(String productFrontEndBase64) {
		this.productFrontEndBase64 = productFrontEndBase64;
	}

}
