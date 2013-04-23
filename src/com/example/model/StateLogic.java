package com.example.model;

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
	
	public StateLogic(ModelImplementation modelImplementation, Boats boats, BombsHandler bombsHandler) {
		this.model = modelImplementation;
		this.boats = boats;
		this.bombsHandler = bombsHandler;
		turn = Player.PLAYER1;
		stage = Stage.PLACE_BOATS;
		showChangingPlayersScreen = false;
		direction = Direction.RIGHT;
		gameOverChecker = new GameOverChecker(bombsHandler.getBombs(), boats);
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
			model.attemptToPlaceBoat(model.getNextBoatToPlace(), orientation);
		}
		else if (stage == Stage.PLACE_BOMB) {
			model.attemptToPlaceBomb(position);
		}
	}
	
	private void handleUpdateTypeButton(Button button) {
		if (button == Button.CHANGE_DIRECTION) {
			flipDirection();
		}
		else if (button == Button.CHANGING_PLAYERS_PAUSESCREEN_NEXT) {
			showChangingPlayersScreen = false;
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
	}
	
	public Direction getDirection() {
		return direction;
	}

}
