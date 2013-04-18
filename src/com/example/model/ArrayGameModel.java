package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ArrayGameModel extends Model {
	
	private Turn turn;
	private Stage stage;
	private boolean showChangingPlayersScreen;
	private Boats boats;
	private BoatCollisionChecker boatCollisionChecker;
	private Direction direction;
	
	public ArrayGameModel() {
		turn = Turn.PLAYER1;
		stage = Stage.PLACE_BOATS;
		boats = new Boats();
		boatCollisionChecker = new BoatCollisionChecker(this);
		direction = Direction.RIGHT;
		showChangingPlayersScreen = false;
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof Position) {
			Orientation orientation = new Orientation((Position) data, direction);
			attemptToPlaceBoat(getNextBoatToPlace(), orientation);
		}
		else if (data instanceof Button) {
			if ((Button) data == Button.CHANGE_DIRECTION) {
				flipDirection();
			}
		}
		setCorrectState();
	}

	private void flipDirection() {
		if (direction == Direction.RIGHT) {
			direction = Direction.UP;
		}
		else {
			direction = Direction.RIGHT;
		}
	}
	
	private void setCorrectState() {
		Stage stage = getStage();
		Turn turn = getTurn();
		if (stage == Stage.PLACE_BOATS && turn == Turn.PLAYER1) {
			if (boats.allBoatsPlacedForPlayer(Player.PLAYER1)) {
				this.turn = Turn.PLAYER2;
				showChangingPlayersScreen = true;
				setChanged();
			}
		}
	}

	public Turn getTurn() {
		return turn;
	}

	public Stage getStage() {
		return stage;
	}

	public Boat getBoat(BoatType boatType, Player player) {
		return boats.getBoat(boatType, player);
	}
	
	public void attemptToPlaceBoat(Boat boatToPlace, Orientation orientation) {
		if (legalPlacementOfBoat(boatToPlace, orientation)) {
			boatToPlace.placeBoat(orientation);
			setChanged();
		}
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

	public boolean showChangingPlayersScreen() {
		return showChangingPlayersScreen;
	}
}
