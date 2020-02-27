package com.ericsson.dto.request;

public class LinkProfileRequestDTO {
	
	private String requesterDeviceId;
	
	private String userName;

	public String getRequesterDeviceId() {
		return requesterDeviceId;
	}

	public void setRequesterDeviceId(String requesterDeviceId) {
		this.requesterDeviceId = requesterDeviceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "LinkProfileRequestDTO [requesterDeviceId=" + requesterDeviceId + ", userName=" + userName + "]";
	}
}
