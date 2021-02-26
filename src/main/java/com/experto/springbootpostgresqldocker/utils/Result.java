package com.experto.springbootpostgresqldocker.utils;

import java.util.ArrayList;

public class Result {
	private ArrayList<Item> lowerDistribution;
	private ArrayList<Item> mediumDistribution;
	private ArrayList<Item> hightDistribution;
	
	public Result() {
		super();
		lowerDistribution = new ArrayList<Item>();
		mediumDistribution = new ArrayList<Item>();
		hightDistribution = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getLowerDistribution() {
		return lowerDistribution;
	}
	public void setLowerDistribution(ArrayList<Item> lowerDistribution) {
		this.lowerDistribution = lowerDistribution;
	}
	public ArrayList<Item> getMediumDistribution() {
		return mediumDistribution;
	}
	public void setMediumDistribution(ArrayList<Item> mediumDistribution) {
		this.mediumDistribution = mediumDistribution;
	}
	public ArrayList<Item> getHightDistribution() {
		return hightDistribution;
	}
	public void setHightDistribution(ArrayList<Item> hightDistribution) {
		this.hightDistribution = hightDistribution;
	}
	
	public void addMin(Item i){
    	lowerDistribution.add(i);
	}
	public void addMed(Item i){
		mediumDistribution.add(i);
	}
	public void addMax(Item i){
		hightDistribution.add(i);
	}
	
	@Override
	public String toString() {
		return "Result [min=" + lowerDistribution.size() + ", med=" + mediumDistribution.size() + ", max=" + hightDistribution.size() + "]";
	}
	
	
}
