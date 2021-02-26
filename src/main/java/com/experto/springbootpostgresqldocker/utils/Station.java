package com.experto.springbootpostgresqldocker.utils;

import java.util.Date;

public class Station {
    private Integer empty_slots;
    private Extra extra;
    private Integer free_bikes;
    private String id;
    private Double latitude;
    private Double longitude;
    private String name;
    private Date timestamp;
    
	public Integer getEmpty_slots() {
		return empty_slots;
	}
	public void setEmpty_slots(Integer empty_slots) {
		this.empty_slots = empty_slots;
	}
	public Extra getExtra() {
		return extra;
	}
	public void setExtra(Extra extra) {
		this.extra = extra;
	}
	public Integer getFree_bikes() {
		return free_bikes;
	}
	public void setFree_bikes(Integer free_bikes) {
		this.free_bikes = free_bikes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
    
    
}