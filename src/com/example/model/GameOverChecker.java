package com.example.model;

public class GameOverChecker {
	
	private Bombs bombs;
	private Boats boats;
	
	public GameOverChecker(Bombs bombs, Boats boats) {
		this.bombs = bombs;
		this.boats = boats;
	}

	public boolean gameIsOver() {
		return allPlayersBoatsAreSunk(Player.PLAYER1) || allPlayersBoatsAreSunk(Player.PLAYER2);
	}

	private boolean allPlayersBoatsAreSunk(Player player) {
		for (Boat boat : boats.getBoats()) {
			if (boat.getPlayer() != player) continue;
			if (!boat.isPlaced()) return false;
			if (!boatIsSunk(boat)) return false;
		}
		return true;
	}

	private boolean boatIsSunk(Boat boat) {
		Player player = boat.getPlayer();
		for (Position position : boat.getBoatPositions()) {
			if (!thereExistsABombAtThisPositionFiredAtPlayer(position, player)) return false;	
		}
		return true;
	}

	public boolean thereExistsABombAtThisPositionFiredAtPlayer(Position position, Player firedAt) {
		for (Bomb bomb : bombs.getPlacedBombs()) {
			if (bomb.getPosition().equals(position)) {
				if (bomb.getPlayerFiredAt().equals(firedAt))
					return true;
			}
		}
		return false;
	}

	public Player getWinner() {
		boolean playerOnesBoatsSunk = allPlayersBoatsAreSunk(Player.PLAYER1);
		boolean playerTwosBoatsSunk = allPlayersBoatsAreSunk(Player.PLAYER2);
		
		if (playerOnesBoatsSunk && playerTwosBoatsSunk) return null;
		else if (playerOnesBoatsSunk) return Player.PLAYER2;
		else if (playerTwosBoatsSunk) return Player.PLAYER1;
		else return null;
	}

	public boolean bombHitShip(Bomb bomb) {
		for (Boat boat : boats.getBoats()) {
			if (boat.getPlayer().equals(bomb.getPlayerFiredAt())) {
				for (Position position : boat.getBoatPositions()) {
					if (position.equals(bomb.getPosition())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
