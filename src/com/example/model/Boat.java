package com.example.model;

public class Boat {
	
	private Orientation orientation;
	private BoatType type;
	private Player owner;
	
	public Boat (BoatType type, Player owner) {
		this.type = type;
		this.owner = owner;
	}
	
	public Position getPosition() {
		return orientation.getPosistion();
	}
	
	public BoatType getType() {
		return type;
	}

	public Player getPlayer() {
		return owner;
	}

	public int getLength() {
		return type.getLength();
	}

	public void placeBoat(Orientation orientation) {
		this.orientation = orientation;
	}

	public boolean legalPlacementOfBoat(Orientation orientation) {
		return false;
	}

	public Direction getDirection() {
		return orientation.getDirection();
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
