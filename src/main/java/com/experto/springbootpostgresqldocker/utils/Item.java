package com.experto.springbootpostgresqldocker.utils;

import java.util.Date;

public class Item {
	private Double latitude;
	private Double longitude;
	private String name;
	private String id ;
	private Integer free_bikes;
	private Date timestamp;
	private Integer slots;
	private Double free_bikes_perc;
	
	
	public Item(Double latitude, Double longitude, String name, String id, Integer free_bikes, Date timestamp,
			Integer slots, Double free_bikes_perc) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.id = id;
		this.free_bikes = free_bikes;
		this.timestamp = timestamp;
		this.slots = slots;
		this.free_bikes_perc = free_bikes_perc;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getFree_bikes() {
		return free_bikes;
	}
	public void setFree_bikes(Integer free_bikes) {
		this.free_bikes = free_bikes;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getSlots() {
		return slots;
	}
	public void setSlots(Integer slots) {
		this.slots = slots;
	}
	public Double getFree_bikes_perc() {
		return free_bikes_perc;
	}
	public void setFree_bikes_perc(Double free_bikes_perc) {
		this.free_bikes_perc = free_bikes_perc;
	}
	

}
