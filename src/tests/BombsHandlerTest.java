package tests;

import junit.framework.TestCase;

import com.example.model.BombsHandler;
import com.example.model.Player;
import com.example.model.Position;

public class BombsHandlerTest extends TestCase {

	public void testLegalPlacementOfBombShouldReturnFalseWhenThereAlreadyIsABombThere() throws Throwable {
		BombsHandler bombsHandler = new BombsHandler();
		Position position = new Position(1, 'j');
		Player player = Player.PLAYER1;
		
		bombsHandler.placeBomb(position, player);
		
		assertFalse(bombsHandler.leagalPlacementOfBomb(position, player));
	}
	
	public void testLegalPlacementOfBombShouldReturnTrueWhenThereIsNoBombThere() throws Throwable {
		BombsHandler bombs = new BombsHandler();
		Position position = new Position(1, 'j');
		Player player = Player.PLAYER1;
		
		assertTrue(bombs.leagalPlacementOfBomb(position, player));
	}
}