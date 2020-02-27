package com.ericsson.dto.response;

public class QRScannedResponseDTO {
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "QRScannedDTO [status=" + status + "]";
	}
}
