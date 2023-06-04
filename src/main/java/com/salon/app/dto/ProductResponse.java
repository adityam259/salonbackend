package com.salon.app.dto;

import java.io.Serializable;

public class ProductResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String image;
	private String title;
	private String time;
	private String rate;
	private String description;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String type;
	private String status;
	private String originalPrice;
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

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", image=" + image + ", title=" + title + ", time=" + time + ", rate="
				+ rate + ", description=" + description + ", image1=" + image1 + ", image2=" + image2 + ", image3="
				+ image3 + ", image4=" + image4 + ", type=" + type + "]";
	}

}
