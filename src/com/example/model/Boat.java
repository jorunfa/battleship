package com.example.model;

public class Boat {
	
	private Position position;
	private Direction direction;
	private BoatType type;
	private Player owner;
	
	public Boat (BoatType type, Player owner) {
		this.type = type;
		this.owner = owner;
	}
	
	public Position getPosition() {
		return position;
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

	public void placeBoat(Position position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	public boolean legalPlacementOfBoat(Position pos, Direction direction) {
		return false;
	}

	public Direction getDirection() {
		return direction;
	}

}
