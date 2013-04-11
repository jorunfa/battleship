package com.example.model;

public class Boat {
	
	private BoatType type;
	private Player owner;
	
	public Boat (BoatType type, Player owner) {
		this.type = type;
		this.owner = owner;
	}
	
	public Position getPosition() {
		return null;
	}
	
	public BoatType getType() {
		return type;
	}

	public Player getPlayer() {
		return owner;
	}

}
