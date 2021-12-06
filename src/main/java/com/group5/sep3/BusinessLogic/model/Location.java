package com.group5.sep3.BusinessLogic.model;

public class Location {
	private int id;
	private String description;

	public Location(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Location{" + "id = " + id + ", description = '" + description + '\'' + '}';
	}
}
