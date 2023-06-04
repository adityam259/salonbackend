package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SubCategory_Product_Duration")
public class SubCategoryProductDuration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subCatId;
	private byte[] subCatImage1;
	private byte[] subCatImage2;
	private byte[] subCatImage3;
	private byte[] subCatImage4;
	private byte[] subCatImage5;
	private String brand;
	private String serviceTime;
	private String price;
	private String title;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(String subCatId) {
		this.subCatId = subCatId;
	}
	public byte[] getSubCatImage1() {
		return subCatImage1;
	}
	public void setSubCatImage1(byte[] subCatImage1) {
		this.subCatImage1 = subCatImage1;
	}
	public byte[] getSubCatImage2() {
		return subCatImage2;
	}
	public void setSubCatImage2(byte[] subCatImage2) {
		this.subCatImage2 = subCatImage2;
	}
	public byte[] getSubCatImage3() {
		return subCatImage3;
	}
	public void setSubCatImage3(byte[] subCatImage3) {
		this.subCatImage3 = subCatImage3;
	}
	public byte[] getSubCatImage4() {
		return subCatImage4;
	}
	public void setSubCatImage4(byte[] subCatImage4) {
		this.subCatImage4 = subCatImage4;
	}
	public byte[] getSubCatImage5() {
		return subCatImage5;
	}
	public void setSubCatImage5(byte[] subCatImage5) {
		this.subCatImage5 = subCatImage5;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
