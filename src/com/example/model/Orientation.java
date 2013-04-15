package com.example.model;

public class Orientation {
	final private Position pos;
	final private Direction direction;
	
	public Orientation(Position pos, Direction direction) {
		this.pos = pos;
		this.direction = direction;
	}

	public Position getPosistion() {
		return pos;
	}

	public Direction getDirection() {
		return direction;
	}

}
