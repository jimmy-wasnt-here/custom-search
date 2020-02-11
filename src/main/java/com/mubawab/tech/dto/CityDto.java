package com.mubawab.tech.dto;

public class CityDto {

	private String name;
	private LocationDto center;

	public CityDto() {
	}

	public CityDto(String name, LocationDto center) {
		this.name = name;
		this.center = center;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationDto getCenter() {
		return center;
	}

	public void setCenter(LocationDto center) {
		this.center = center;
	}

}
