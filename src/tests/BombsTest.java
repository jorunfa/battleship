package tests;

import junit.framework.TestCase;

import com.example.model.Bombs;
import com.example.model.Player;
import com.example.model.Position;

public class BombsTest extends TestCase {

	public void testLegalPlacementOfBombShouldReturnFalseWhenThereAlreadyIsABombThere() throws Throwable {
		Bombs bombs = new Bombs();
		Position position = new Position(1, 'j');
		Player player = Player.PLAYER1;
		
		bombs.placeBomb(position, player);
		
		assertFalse(bombs.leagalPlacementOfBomb(position, player));
	}
	
	public void testLegalPlacementOfBombShouldReturnTrueWhenThereIsNoBombThere() throws Throwable {
		Bombs bombs = new Bombs();
		Position position = new Position(1, 'j');
		Player player = Player.PLAYER1;
		
		assertTrue(bombs.leagalPlacementOfBomb(position, player));
	}
}