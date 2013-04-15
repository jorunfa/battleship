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
		if (checkIsToCloseToRightWall(orientation)) return false;
		if (checkIsToCloseToTopWall(orientation)) return false;
		return true;
	}

	private boolean checkIsToCloseToRightWall(Orientation orientation) {
		int boatsRightEdge = calculateBoatsRightEdgeFromOrientation(orientation);
		if (boatsRightEdge <= 10) return false;
		else return true;
	}

	private int calculateBoatsRightEdgeFromOrientation(Orientation orientation) {
		int boatLength = getLength();
		Position pos = orientation.getPosistion();
		int column = pos.getColumn();
		Direction direction = orientation.getDirection();
		
		if (direction == Direction.UP) return column;
		else return column + boatLength - 1;
	}

	private boolean checkIsToCloseToTopWall(Orientation orientation) {
		char boatsTopEdge = calculateBoatsTopEdgeFromOrientation(orientation);
		if (boatsTopEdge >= 'a') return false;
		else return true;
	}

	private char calculateBoatsTopEdgeFromOrientation(Orientation orientation) {
		int boatLength = getLength();
		Position pos = orientation.getPosistion();
		char row = pos.getRow();
		Direction direction = orientation.getDirection();
		
		if (direction == Direction.RIGHT) return row;
		else return (char) (row - boatLength - 1);
	}

	public Direction getDirection() {
		return orientation.getDirection();
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
