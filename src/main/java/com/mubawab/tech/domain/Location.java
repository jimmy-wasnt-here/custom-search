package com.mubawab.tech.domain;

import javax.persistence.Embeddable;

import com.google.gson.annotations.SerializedName;

@Embeddable
public class Location {

	@SerializedName("long")
	private Double longitude;
	@SerializedName("lat")
	private Double latitude;

	public Location() {

	}

	public Location(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
