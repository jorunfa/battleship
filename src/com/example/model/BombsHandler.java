package com.example.model;

public class BombsHandler {
	
	Bombs bombs;
	Player lastBombPlacedBy;
	
	public BombsHandler() {
		bombs = new Bombs();
	}
	
	public void placeBomb(Position position, Player player) {
		bombs.placeBomb(position, player);
		lastBombPlacedBy = player;
	}

	public boolean leagalPlacementOfBomb(Position position, Player turn) {
		return bombs.leagalPlacementOfBomb(position, turn);
	}

	public Player lastBombPlacedBy() {
		return lastBombPlacedBy;
	}

}
