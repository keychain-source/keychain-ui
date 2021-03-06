package com.ericsson.dto.response;

public class GetQRRegistryResponseDTO {
	private String user_id;
	
	private String qr_registration_status;
	
	private String qr_code;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getQr_registration_status() {
		return qr_registration_status;
	}

	public void setQr_registration_status(String qr_registration_status) {
		this.qr_registration_status = qr_registration_status;
	}

	
	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	@Override
	public String toString() {
		return "GetQRRegistryResponseDTO [user_id=" + user_id + ", qr_registration_status=" + qr_registration_status
				+ ", qr_code=" + qr_code + "]";
	}
}
