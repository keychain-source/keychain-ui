package com.ericsson.dto.request;

import javax.validation.constraints.Null;

public class SignUpUserProfileRequestDTO {
	
	private String userEmail;
	
	private String userPassword;
	
	private String userName;
	
	@Null
	private String userMailAddress;
	
	@Null
	private String userBillingAddress;
	
	@Null
	private String userId;
	
	private String userFirstName;
	
	private String userLastName;
	
	@Null
	private String createTimestamp;
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMailAddress() {
		return userMailAddress;
	}

	public void setUserMailAddress(String userMailAddress) {
		this.userMailAddress = userMailAddress;
	}

	public String getUserBillingAddress() {
		return userBillingAddress;
	}

	public void setUserBillingAddress(String userBillingAddress) {
		this.userBillingAddress = userBillingAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	@Override
	public String toString() {
		return "SignUpUserProfileRequestDTO [userEmail=" + userEmail + ", userPassword=" + userPassword + ", userName="
				+ userName + ", userMailAddress=" + userMailAddress + ", userBillingAddress=" + userBillingAddress
				+ ", userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", createTimestamp=" + createTimestamp + "]";
	}
	
}
