package com.mubawab.tech.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Town {

	@Id
	private Long id;

	@Column(insertable = false, updatable = false)
	private Long cityId;

	@OneToOne
	@JoinColumn(name = "cityId", referencedColumnName = "id")
	private City city;

	@Column(columnDefinition = "TEXT")
	private String name;

	@Embedded
	@Column(name = "center")
	@AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "lat")),
			@AttributeOverride(name = "longitude", column = @Column(name = "long")) })
	private Location center;

	public Town() {
	}

	public Town(String name, Location center) {
		this.name = name;
		this.center = center;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getCenter() {
		return center;
	}

	public void setCenter(Location center) {
		this.center = center;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
