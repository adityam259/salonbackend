package com.salon.app.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sub_category_master")
public class SubCategoryMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subCatName;
	private String imageUploadDirectory;
	private String imageUploadDirectory2;
	private String imageUploadDirectory3;
	private String imageUploadDirectory4;
	private String imageUploadDirectory5;
	private String imageName;
	private String imageName2;
	private String imageName3;
	private String imageName4;
	private String imageName5;
	private String categoryId;
	private String brand;
	private String serviceTime;
	private String price;
	private String title;
	private String description;
	private String originalPrice;
	private String isDelete;
	@Transient
	private String frontEndBase64;
	@Transient
	private String frontEndBase64One;
	@Transient
	private String frontEndBase64Two;
	@Transient
	private String frontEndBase64Three;
	@Transient
	private String frontEndBase64Four;
	@Transient
	private String catName;
	@Transient
	private String type;
	@Transient
	private String quantity;
	private String membershipDiscount;
	private String membershipDiscount2;

	public String getImageUploadDirectory() {
		return imageUploadDirectory;
	}

	public void setImageUploadDirectory(String imageUploadDirectory) {
		this.imageUploadDirectory = imageUploadDirectory;
	}

	public String getImageUploadDirectory2() {
		return imageUploadDirectory2;
	}

	public void setImageUploadDirectory2(String imageUploadDirectory2) {
		this.imageUploadDirectory2 = imageUploadDirectory2;
	}

	public String getImageUploadDirectory3() {
		return imageUploadDirectory3;
	}

	public void setImageUploadDirectory3(String imageUploadDirectory3) {
		this.imageUploadDirectory3 = imageUploadDirectory3;
	}

	public String getImageUploadDirectory4() {
		return imageUploadDirectory4;
	}

	public void setImageUploadDirectory4(String imageUploadDirectory4) {
		this.imageUploadDirectory4 = imageUploadDirectory4;
	}

	public String getImageUploadDirectory5() {
		return imageUploadDirectory5;
	}

	public void setImageUploadDirectory5(String imageUploadDirectory5) {
		this.imageUploadDirectory5 = imageUploadDirectory5;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageName2() {
		return imageName2;
	}

	public void setImageName2(String imageName2) {
		this.imageName2 = imageName2;
	}

	public String getImageName3() {
		return imageName3;
	}

	public void setImageName3(String imageName3) {
		this.imageName3 = imageName3;
	}

	public String getImageName4() {
		return imageName4;
	}

	public void setImageName4(String imageName4) {
		this.imageName4 = imageName4;
	}

	public String getImageName5() {
		return imageName5;
	}

	public void setImageName5(String imageName5) {
		this.imageName5 = imageName5;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getFrontEndBase64One() {
		return frontEndBase64One;
	}

	public void setFrontEndBase64One(String frontEndBase64One) {
		this.frontEndBase64One = frontEndBase64One;
	}

	public String getFrontEndBase64Two() {
		return frontEndBase64Two;
	}

	public void setFrontEndBase64Two(String frontEndBase64Two) {
		this.frontEndBase64Two = frontEndBase64Two;
	}

	public String getFrontEndBase64Three() {
		return frontEndBase64Three;
	}

	public void setFrontEndBase64Three(String frontEndBase64Three) {
		this.frontEndBase64Three = frontEndBase64Three;
	}

	public String getFrontEndBase64Four() {
		return frontEndBase64Four;
	}

	public void setFrontEndBase64Four(String frontEndBase64Four) {
		this.frontEndBase64Four = frontEndBase64Four;
	}

	public String getFrontEndBase64() {
		return frontEndBase64;
	}

	public void setFrontEndBase64(String frontEndBase64) {
		this.frontEndBase64 = frontEndBase64;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
