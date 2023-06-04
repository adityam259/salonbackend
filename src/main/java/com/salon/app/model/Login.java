package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String password;
	private String lastLogin;
	private String token;
	private String gender;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String address;
	private String profileImage;
	private String emailId;
	private String type;
	private byte[] image;
	@Transient
	private String verificationCode;
	@Transient
	private String frontendBase64;
	private String alternatephoneNo;
	private String dob;
	private String dateOfAnniversary;
	private String isDelete;
	private String isMember;
	private String memberPlanId;
	private String memberStartDate;
	private String memberEndDate;
	
	
	public String getMemberStartDate() {
		return memberStartDate;
	}

	public void setMemberStartDate(String memberStartDate) {
		this.memberStartDate = memberStartDate;
	}

	public String getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(String memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public String getMemberPlanId() {
		return memberPlanId;
	}

	public void setMemberPlanId(String memberPlanId) {
		this.memberPlanId = memberPlanId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getDateOfAnniversary() {
		return dateOfAnniversary;
	}

	public void setDateOfAnniversary(String dateOfAnniversary) {
		this.dateOfAnniversary = dateOfAnniversary;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAlternatephoneNo() {
		return alternatephoneNo;
	}

	public void setAlternatephoneNo(String alternatephoneNo) {
		this.alternatephoneNo = alternatephoneNo;
	}

	public String getFrontendBase64() {
		return frontendBase64;
	}

	public void setFrontendBase64(String frontendBase64) {
		this.frontendBase64 = frontendBase64;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", userName=" + userName + ", password=" + password + ", lastLogin=" + lastLogin
				+ ", token=" + token + ", gender=" + gender + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", profileImage=" + profileImage + ", emailId="
				+ emailId + ", verificationCode=" + verificationCode + "]";
	}

}
