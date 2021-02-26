package com.experto.springbootpostgresqldocker.utils;

import java.util.List;

public class Network{
	private List<String> company;
	private String href;
	private String id;
	private Location location;
	private String name;
	private List<Station> stations;
	
	public List<String> getCompany() {
		return company;
	}
	public void setCompany(List<String> company) {
		this.company = company;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	
}