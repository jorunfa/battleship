package com.example.model;

public class BombsHandler {
	
	private Bombs bombs;
	private Player lastBombPlacedBy;
	
	public BombsHandler() {
		bombs = new Bombs();
	}
	
	public void placeBomb(Position position, Player player) {
		bombs.placeBomb(position, player);
		if (player == Player.PLAYER1) {
			lastBombPlacedBy = Player.PLAYER2;
		}
		else {
			lastBombPlacedBy = Player.PLAYER1;
		}
		
	}

	public boolean leagalPlacementOfBomb(Position position, Player turn) {
		return bombs.leagalPlacementOfBomb(position, turn);
	}

	public Player lastBombPlacedBy() {
		return lastBombPlacedBy;
	}
	
	public Bombs getBombs() {
		return bombs;
	}
}
