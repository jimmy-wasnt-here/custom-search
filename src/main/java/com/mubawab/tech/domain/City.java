package com.mubawab.tech.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	private Long id;

	@Column
	private String name;

	@Embedded
	@Column(name = "center")
	@AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "lat")),
			@AttributeOverride(name = "longitude", column = @Column(name = "long")) })
	private Location center;

	public City() {
	}

	public City(String name, Location center) {
		this.name = name;
		this.center = center;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
