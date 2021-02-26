package com.experto.springbootpostgresqldocker.utils;

import java.util.List;

public class Extra{
	private List<String> bike_uids;
	private String number;
	private Integer slots;
	private String uid;
	
	public List<String> getBike_uids() {
		return bike_uids;
	}
	public void setBike_uids(List<String> bike_uids) {
		this.bike_uids = bike_uids;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getSlots() {
		return slots;
	}
	public void setSlots(Integer slots) {
		this.slots = slots;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}