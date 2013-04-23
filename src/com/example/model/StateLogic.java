package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class StateLogic {
	
	private ModelImplementation model;
	private Boats boats;
	private BombsHandler bombsHandler;
	private Player turn;
	private Stage stage;
	private Direction direction;
	private boolean showChangingPlayersScreen;
	private GameOverChecker gameOverChecker;
	private BoatCollisionChecker boatCollisionChecker;
	
	public StateLogic(ModelImplementation model) {
		this.model = model;
		initializeEverythingToStart();
	}
	
	private void initializeEverythingToStart() {
		boats = new Boats();
		bombsHandler = new BombsHandler();
		turn = Player.PLAYER1;
		stage = Stage.PLACE_BOATS;
		direction = Direction.RIGHT;
		showChangingPlayersScreen = false;
		gameOverChecker = new GameOverChecker(bombsHandler.getBombs(), boats);
		boatCollisionChecker = new BoatCollisionChecker(this);
	}

	public void update(Observable observable, Object data) {
		if (data instanceof Position) {
			handleUpdateTypePosistion((Position) data);
			for (Boat boat : model.getBoats()) {
				System.out.println(boat);
			}
		}
		else if (data instanceof Button) {
			handleUpdateTypeButton((Button) data);
		}
		setCorrectState();
	}
	
	private void handleUpdateTypePosistion(Position position) {
		if (stage == Stage.PLACE_BOATS) {
			Orientation orientation = new Orientation(position, direction);
			attemptToPlaceBoat(model.getNextBoatToPlace(), orientation);
		}
		else if (stage == Stage.PLACE_BOMB) {
			attemptToPlaceBomb(position);
		}
	}
	
	private void handleUpdateTypeButton(Button button) {
		if (button == Button.CHANGE_DIRECTION) {
			flipDirection();
		}
		else if (button == Button.CHANGING_PLAYERS_PAUSESCREEN_NEXT) {
			showChangingPlayersScreen = false;
		}
		else if (button == Button.RESTART) {
			initializeEverythingToStart();
		}
	}

	private void setCorrectState() {
		if (playerFinishedPlacingBoats(Player.PLAYER1)) {
			transitToPlayerTwosTurnToPlaceBoats();
		}
		else if (playerFinishedPlacingBoats(Player.PLAYER2)) {
			transitToPlayerOnesTurnToPlaceBombs();
		}
		else if (isPlayersTurnToPlaceBomb(Player.PLAYER2)) {
			transitToPlayersTurn(Player.PLAYER2);
		}
		else if (isPlayersTurnToPlaceBomb(Player.PLAYER1)) {
			transitToPlayersTurn(Player.PLAYER1);
		}
		else if (gameIsOver()) transitToGameOver();
	}

	private boolean playerFinishedPlacingBoats(Player player) {
		return ((turn == player) &&
				(boats.allBoatsPlacedForPlayer(player)) &&
				(stage == Stage.PLACE_BOATS));
	}
	
	private void transitToPlayerTwosTurnToPlaceBoats() {
		this.turn = Player.PLAYER2;
		setShowChangingPlayersScreen();
	}
	
	private void setShowChangingPlayersScreen() {
		showChangingPlayersScreen = true;
	}
	
	private void transitToPlayerOnesTurnToPlaceBombs() {
		this.stage = Stage.PLACE_BOMB;
		this.turn = Player.PLAYER1;
		setShowChangingPlayersScreen();
	}

	private boolean isPlayersTurnToPlaceBomb(Player player) {
		if (stage == Stage.PLACE_BOMB) {
			if (!(turn == player)) {
				if (!(bombsHandler.lastBombPlacedBy() == player)) return true;
			}
		}
		return false;
	}

	private void transitToPlayersTurn(Player player) {
		this.turn = player;
		setShowChangingPlayersScreen();
	}

	private boolean gameIsOver() {
		return gameOverChecker.gameIsOver();
	}
	
	private void transitToGameOver() {
		this.stage = Stage.GAME_OVER;	
	}
	
	public Player getTurn() {
		return turn;
	}

	public Stage getStage() {
		return stage;
	}
	
	public boolean showChangingPlayersScreen() {
		return showChangingPlayersScreen;
	}
	
	private void flipDirection() {
		if (direction == Direction.RIGHT) {
			direction = Direction.UP;
		}
		else {
			direction = Direction.RIGHT;
		}
		model.setStateChanged();
	}
	
	public Direction getDirection() {
		return direction;
	}

	public Boat getBoat(BoatType boatType, Player player) {
		return boats.getBoat(boatType, player);
	}
	
	public void attemptToPlaceBoat(Boat boatToPlace, Orientation orientation) {
		if (legalPlacementOfBoat(boatToPlace, orientation)) {
			boatToPlace.placeBoat(orientation);
			model.setStateChanged();
		}
	}
	
	public boolean legalPlacementOfBoat(Boat boatToPlace, Orientation orientation) {
		if (!boatToPlace.legalPlacementOfBoat(orientation)) return false;
		if (!boatCollisionChecker.leagalPlacementOfBoat(boatToPlace, orientation)) return false;
		return true;
	}
	
	public void attemptToPlaceBomb(Position position) {
		if (legalPlacementOfBomb(position)) {
			placeBomb(position);
			model.setStateChanged();
		}
	}

	public boolean legalPlacementOfBomb(Position position) {
		return bombsHandler.leagalPlacementOfBomb(position, getTurn());
	}

	private void placeBomb(Position position) {
		bombsHandler.placeBomb(position, getTurn());
	}

	public ArrayList<Boat> getBoats() {
		return boats.getBoats();
	}

	public Boat getNextBoatToPlace() {
		return boats.getNextBoatToPlace();
	}
}
