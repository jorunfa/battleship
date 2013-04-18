package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ArrayGameModel extends Model {
	
	private ModelTurn turn;
	private ModelStage stage;
	private Boats boats;
	private BoatCollisionChecker boatCollisionChecker;
	private Direction direction;
	
	public ArrayGameModel() {
		turn = ModelTurn.PLAYER1;
		stage = ModelStage.PLACE_BOATS;
		boats = new Boats();
		boatCollisionChecker = new BoatCollisionChecker(this);
		direction = Direction.RIGHT;
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof Position) {
			System.out.println(((Position) data).getColumn());
			System.out.println(((Position) data).getRow());
		}
		else if (data instanceof Button) {
			if ((Button) data == Button.ChangeDirection) {
				flipDirection();
			}
		}
	}

	private void flipDirection() {
		if (direction == Direction.RIGHT) {
			direction = Direction.UP;
		}
		else {
			direction = Direction.RIGHT;
		}
	}

	public ModelTurn getTurn() {
		return turn;
	}

	public ModelStage getStage() {
		return stage;
	}

	public Boat getBoat(BoatType boatType, Player player) {
		return boats.getBoat(boatType, player);
	}
	
	public void attemptToPlaceBoat(Boat boatToPlace, Orientation orientation) {
		if (legalPlacementOfBoat(boatToPlace, orientation))
			boatToPlace.placeBoat(orientation); 
	}
	
	public boolean legalPlacementOfBoat(Boat boatToPlace, Orientation orientation) {
		if (!boatToPlace.legalPlacementOfBoat(orientation)) return false;
		if (!boatCollisionChecker.leagalPlacementOfBoat(boatToPlace, orientation)) return false;
		return true;
	}

	public Direction getDirection() {
		return direction;
	}

	public ArrayList<Boat> getBoats() {
		return boats.getBoats();
	}

	public Boat getNextBoatToPlace() {
		return boats.getNextBoatToPlace();
	}
}
