package com.example.model;

public enum BoatType {
	AIRCRAFT_CARRIER (5),
	BATTLESHIP (4),
	SUBMARINE (3),
	DESTROYER (3),
	PATROL_BOAT (2);
	
	private final int length;
	
	BoatType(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}
}
