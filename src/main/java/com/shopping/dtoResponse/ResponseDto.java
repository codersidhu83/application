package com.shopping.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

	private String status;
	private Object message;
	public ResponseDto(String status, Object message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ResponseDto() {
	
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
	
	
	

	
	
	
	
	
}
