package com.mubawab.tech.dto;

public class AdDto {

	public AdDto(){

	}

	public AdDto(String title, String description, Float price, String city, String town, String category, String subCategory, int qualityScore) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.city = city;
		this.town = town;
		this.category = category;
		this.subCategory = subCategory;
		this.qualityScore = qualityScore;
	}

	private String title;

	private String description;

	private Float price;

	private String city;

	private String town;

	private String category;

	private String subCategory;

	private int qualityScore;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public int getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(int qualityScore) {
		this.qualityScore = qualityScore;
	}
}
