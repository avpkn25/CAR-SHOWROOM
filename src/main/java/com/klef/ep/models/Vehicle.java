package com.klef.ep.models;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Vehicle implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String enginetype;
	@Column(nullable = false)
	private String fueltype;
	@Column(nullable = false)
	private int seatingcapacity;
	
	@Lob
	@Column(nullable = false)
	private Blob imagedata;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEnginetype() {
		return enginetype;
	}

	public void setEnginetype(String enginetype) {
		this.enginetype = enginetype;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public int getSeatingcapacity() {
		return seatingcapacity;
	}

	public void setSeatingcapacity(int seatingcapacity) {
		this.seatingcapacity = seatingcapacity;
	}

	public Blob getImagedata() {
		return imagedata;
	}

	public void setImagedata(Blob imagedata) {
		this.imagedata = imagedata;
	}
}
