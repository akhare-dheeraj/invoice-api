package com.invoice.api.helper.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("error")
public class ErrorDto {
	
	@JsonProperty
	protected String message;
	
	@JsonProperty
	protected int code;

	static enum Status {
		NOT_FOUND(404, "Server is ready, but entry not found"), INTERNAL_ERROR(101, "Server internal error");
		
		int code;
		String message;

		private Status(int code, String message) {
			this.code = code;
			this.message = message;
		}
	}

	public ErrorDto() {
		super();
	}

	public ErrorDto(Status status) {
		super();
		this.code = status.code;
		this.message = status.message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
