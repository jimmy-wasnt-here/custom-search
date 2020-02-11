package com.mubawab.tech.domain;

import javax.persistence.*;

@Entity
public class Ad {

	/*
	* {
      "id" : 100,
      "title" : "Appartement 2 Chambres 115m², Meublé, Mhamid",
      "description" : "Je met en location par nuité appartement propre meublé 115m² avec grande terrasse, parking sécurisé et sécurité h24. Endroit calme avec jardin. Près de toute commodités, 15 minute de la place jamaa lefna.",
      "price" : 0,
      "cityId" : 824,
      "townId" : 2290,
      "categoryId" : 3,
      "subCategoryId" : 4,
      "qualityScore" : 38
   }
	* */


	@Id
	private Long id;
	@Column
	private String title;
	@Column(length = 2000)
	private String description;
	@Column
	private Float price;
	@Column
	private int qualityScore;

	//Foreign keys
	@Column(insertable = false, updatable = false)
	private Long cityId;
	@Column(insertable = false, updatable = false)
	private Long townId;
	@Column(insertable = false, updatable = false)
	private Long subCategoryId;
	@Column(insertable = false, updatable = false)
	private Long categoryId;

	@OneToOne
	@JoinColumn(name = "cityId", referencedColumnName = "id")
	private City city;

	@OneToOne
	@JoinColumn(name = "townId", referencedColumnName = "id")
	private Town town;

	@OneToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;

	@OneToOne
	@JoinColumn(name = "subCategoryId", referencedColumnName = "id")
	private Subcategory subcategory;

	public Ad() {
	}

	public Ad(String title, String description, Float price, Long cityId, Long townId, Long categoryId, Long subCategoryId, int qualityScore) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.cityId = cityId;
		this.townId = townId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.qualityScore = qualityScore;
	}

	public Long getId() {
		return id;
	}

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public int getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(int qualityScore) {
		this.qualityScore = qualityScore;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getTownId() {
		return townId;
	}

	public void setTownId(Long townId) {
		this.townId = townId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
}
