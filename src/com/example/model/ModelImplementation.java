package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ModelImplementation extends Model {
	
	private Player turn;
	private Stage stage;
	private boolean showChangingPlayersScreen;
	private Boats boats;
	private BoatCollisionChecker boatCollisionChecker;
	private Direction direction;
	
	public ModelImplementation() {
		turn = Player.PLAYER1;
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
			Button button = (Button) data;
			if (button == Button.CHANGE_DIRECTION) {
				flipDirection();
			}
			else if (button == Button.CHANGING_PLAYERS_PAUSESCREEN_NEXT) {
				showChangingPlayersScreen = false;
				setChanged();
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
		if (playerFinishedPlacingBoats(Player.PLAYER1)) {
			transitToPlayerTwosTurnToPlaceBoats();
		}
		else if (playerFinishedPlacingBoats(Player.PLAYER2)) {
			transitToPlayerOnesTurnToPlaceBombs();
		}
	}

	private boolean playerFinishedPlacingBoats(Player player) {
		return ((turn == player) && (boats.allBoatsPlacedForPlayer(player)) && (stage == Stage.PLACE_BOATS));
	}
	
	private void transitToPlayerTwosTurnToPlaceBoats() {
		this.turn = Player.PLAYER2;
		showChangingPlayersScreen = true;
		setChanged();
	}
	
	private void transitToPlayerOnesTurnToPlaceBombs() {
		this.stage = Stage.PLACE_BOMB;
		this.turn = Player.PLAYER1;
		showChangingPlayersScreen = true;
		setChanged();
	}

	public Player getTurn() {
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
