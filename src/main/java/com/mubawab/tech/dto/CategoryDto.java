package com.mubawab.tech.dto;

public class CategoryDto {

	private String code;
	private String name;

	public CategoryDto() {
	}

	public CategoryDto(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
