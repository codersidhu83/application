package com.shopping.dtoResponse;

import lombok.Data;

@Data
public class ProductResponseDto {

	private String name;

	private String code;

	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public ProductResponseDto(String name, String code, String brand) {
		super();
		this.name = name;
		this.code = code;
		this.brand = brand;
	}

	public ProductResponseDto() {
	
	}

	
	
	
	
	
}
