package com.ericsson.dto.response;

public class GenerateQrResponseDTO {
	private String qrCode;
	
	private String qrCodeUrl;
	
	private String requesterSystemId;

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getRequesterSystemId() {
		return requesterSystemId;
	}

	public void setRequesterSystemId(String requesterSystemId) {
		this.requesterSystemId = requesterSystemId;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	@Override
	public String toString() {
		return "GenerateQrResponseDTO [qrCode=" + qrCode + ", qrCodeUrl=" + qrCodeUrl + ", requesterSystemId="
				+ requesterSystemId + "]";
	}
	
}
