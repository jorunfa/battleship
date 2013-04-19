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
	private BombsHandler bombsHandler;
	
	public ModelImplementation() {
		turn = Player.PLAYER1;
		stage = Stage.PLACE_BOATS;
		boats = new Boats();
		boatCollisionChecker = new BoatCollisionChecker(this);
		direction = Direction.RIGHT;
		showChangingPlayersScreen = false;
		bombsHandler = new BombsHandler();
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof Position) {
			Position position = (Position) data;
			if (stage == Stage.PLACE_BOATS) {
				Orientation orientation = new Orientation(position, direction);
				attemptToPlaceBoat(getNextBoatToPlace(), orientation);
			}
			else if (stage == Stage.PLACE_BOMB) {
				attemptToPlaceBomb(position);
			}
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
		else if (isPlayerTwosTurnToPlaceBomb()) {
			transitToPlayerTwosTurnToPlaceBobms();
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
		setShowChangingPlayersScreen();
		setChanged();
	}
	
	private void setShowChangingPlayersScreen() {
		showChangingPlayersScreen = true;
	}

	private boolean isPlayerTwosTurnToPlaceBomb() {
		if (stage == Stage.PLACE_BOMB) {
			if (turn == Player.PLAYER1) {
				if (bombsHandler.lastBombPlacedBy() == Player.PLAYER1) return true;
			}
		}
		return false;
	}
	
	private void transitToPlayerTwosTurnToPlaceBobms() {
		this.turn = Player.PLAYER2;
		setShowChangingPlayersScreen();
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
	
	public void attemptToPlaceBomb(Position position) {
		if (legalPlacementOfBomb(position)) {
			bombsHandler.placeBomb(position);
			setChanged();
		}
	}
	
	public boolean legalPlacementOfBomb(Position position) {
		return bombsHandler.leagalPlacementOfBomb(position);
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
