package com.salon.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_master")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private String productId;
	private String totalAmount;
	private String orderTrackingId;
	private String type;
	private String status;
	private String date;
	private String timeSlot;
	private String deliverNotify;
	private String paymentMode;
	@Transient
	private String frontEndBase64;
	@Transient
	private String title;
	@Transient
	private String userName;
	@Transient
	private String userAddress;
	@Transient
	private String userMobile;
	@Transient
	private String userEmail;
	@Transient
	private String fullName;
	@Transient
	private String originalPirce;
	@Transient
	private String description;

	private String quantity;

	private String totalAmountOriginal;

	public String getTotalAmountOriginal() {
		return totalAmountOriginal;
	}

	public void setTotalAmountOriginal(String totalAmountOriginal) {
		this.totalAmountOriginal = totalAmountOriginal;
	}

	public String getDeliverNotify() {
		return deliverNotify;
	}

	public void setDeliverNotify(String deliverNotify) {
		this.deliverNotify = deliverNotify;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getOriginalPirce() {
		return originalPirce;
	}

	public void setOriginalPirce(String originalPirce) {
		this.originalPirce = originalPirce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFrontEndBase64() {
		return frontEndBase64;
	}

	public void setFrontEndBase64(String frontEndBase64) {
		this.frontEndBase64 = frontEndBase64;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderTrackingId() {
		return orderTrackingId;
	}

	public void setOrderTrackingId(String orderTrackingId) {
		this.orderTrackingId = orderTrackingId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
