package com.ericsson.dto;

public class ErrorResponseDTO {
	private String errorCode;
	private String errorDesc;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String string) {
		this.errorCode = string;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
