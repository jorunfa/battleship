package com.example.model;

import java.util.ArrayList;
import java.util.Observable;

public class ModelImplementation extends Model {
	
	private Boats boats;
	private BombsHandler bombsHandler;
	private Player turn;
	private Stage stage;
	private Direction direction;
	private boolean showChangingPlayersScreen;
	private boolean showOwnBoard;
	private GameOverChecker gameOverChecker;
	private BoatCollisionChecker boatCollisionChecker;
	
	public ModelImplementation() {
		initializeEverythingToStart();
	}
	
	private void initializeEverythingToStart() {
		boats = new Boats();
		bombsHandler = new BombsHandler();
		turn = Player.PLAYER1;
		stage = Stage.PLACE_BOATS;
		direction = Direction.RIGHT;
		showChangingPlayersScreen = false;
		showOwnBoard = false;
		gameOverChecker = new GameOverChecker(bombsHandler.getBombs(), boats);
		boatCollisionChecker = new BoatCollisionChecker(this);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof Position && !showChangingPlayersScreen && !showOwnBoard) {
			handleUpdateTypePosistion((Position) data);
		}
		else if (data instanceof Button) {
			handleUpdateTypeButton((Button) data);
		}
		setCorrectState();
		notifyObservers();
	}
	
	private void handleUpdateTypePosistion(Position position) {
		if (stage == Stage.PLACE_BOATS) {
			Orientation orientation = new Orientation(position, direction);
			attemptToPlaceBoat(getNextBoatToPlace(), orientation);
		}
		else if (stage == Stage.PLACE_BOMB) {
			attemptToPlaceBomb(position);
		}
	}
	
	private void handleUpdateTypeButton(Button button) {
		if (button == Button.CHANGE_DIRECTION && stage == Stage.PLACE_BOATS) {
			flipDirection();
		}
		else if (button == Button.CHANGING_PLAYERS_PAUSESCREEN_NEXT) {
			showChangingPlayersScreen = false;
			setChanged();
		}
		else if (button == Button.RESTART && stage == Stage.GAME_OVER) {
			initializeEverythingToStart();
			setChanged();
		}
		else if (button == Button.SHOW_OWN_BOARD_FLIP && !showChangingPlayersScreen && stage == Stage.PLACE_BOMB) {
			flipShowOwnBoard();
		}
	}
	
	private void flipShowOwnBoard() {
		showOwnBoard = !showOwnBoard;
		setChanged();
	}

	private void setCorrectState() {
		if (playerFinishedPlacingBoats(Player.PLAYER1)) {
			transitToPlayersTurn(Player.PLAYER2);
		}
		else if (playerFinishedPlacingBoats(Player.PLAYER2)) {
			this.stage = Stage.PLACE_BOMB;
			transitToPlayersTurn(Player.PLAYER1);
		}
		else if (gameIsOver()) {
			transitToGameOver();
		}
		else if (isPlayersTurnToPlaceBomb(Player.PLAYER2)) {
			transitToPlayersTurn(Player.PLAYER2);
		}
		else if (isPlayersTurnToPlaceBomb(Player.PLAYER1)) {
			transitToPlayersTurn(Player.PLAYER1);
		}
	}

	private boolean playerFinishedPlacingBoats(Player player) {
		return ((turn == player) &&
				(boats.allBoatsPlacedForPlayer(player)) &&
				(stage == Stage.PLACE_BOATS));
	}
	
	private void transitToPlayersTurn(Player player) {
		this.turn = player;
		setShowChangingPlayersScreen();
		setChanged();
	}
	
	private void setShowChangingPlayersScreen() {
		showChangingPlayersScreen = true;
	}

	private boolean isPlayersTurnToPlaceBomb(Player player) {
		if (stage == Stage.PLACE_BOMB) {
			if (turn != player) {
				if (bombsHandler.lastBombPlacedBy() != player) {
					if (bombsHandler.lastBombPlacedBy() != null)
						return true;
				}
			}
		}
		return false;
	}

	private boolean gameIsOver() {
		return gameOverChecker.gameIsOver();
	}
	
	private void transitToGameOver() {
		this.stage = Stage.GAME_OVER;	
		setChanged();
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
		setChanged();
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
			placeBomb(position);
			setChanged();
		}
	}

	public boolean legalPlacementOfBomb(Position position) {
		Player firedAt = getPlayerWhichTurnItIsnt();
		return bombsHandler.leagalPlacementOfBomb(position, firedAt);
	}

	public Player getPlayerWhichTurnItIsnt() {
		if (getTurn() == Player.PLAYER1) return Player.PLAYER2;
		else return Player.PLAYER1;
	}

	private void placeBomb(Position position) {
		bombsHandler.placeBomb(position, getPlayerWhichTurnItIsnt());
	}

	public ArrayList<Boat> getBoats() {
		return boats.getBoats();
	}

	public Boat getNextBoatToPlace() {
		return boats.getNextBoatToPlace();
	}

	public ArrayList<Bomb> getPlacedBombs() {
		return bombsHandler.getBombs().getPlacedBombs();
	}

	@Override
	public boolean viewOwnBoard() {
		return showOwnBoard;
	}

	@Override
	public boolean bombHitShip(Bomb bomb) {
		return gameOverChecker.bombHitShip(bomb);
	}

	@Override
	public Player getWinner() {
		return gameOverChecker.getWinner();
	}
}
