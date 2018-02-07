package com.java.pointwest.bean;

public class Location {

	private String buildingId;
	private String buildingName;
	private String buildingAddress;
	private Seat seat;
	
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBuildingAddress() {
		return buildingAddress;
	}
	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}



}