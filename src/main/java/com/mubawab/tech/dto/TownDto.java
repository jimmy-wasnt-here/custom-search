package com.mubawab.tech.dto;

public class TownDto {

	private String name;
	private LocationDto center;
	private String city;

	public TownDto() {
	}

	public TownDto(String name, LocationDto center, String city) {
		this.name = name;
		this.center = center;
		this.city = city;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
