package tests;

import com.example.model.Bomb;
import com.example.model.Player;
import com.example.model.Position;

import junit.framework.TestCase;

public class BombTest extends TestCase {

	public void testBombEqualsMethod() throws Throwable {
		Position position = new Position(1, 'j');
		Bomb bomb1 = new Bomb(position, Player.PLAYER1);
		Bomb bomb2 = new Bomb(position, Player.PLAYER1);
		
		assertTrue(bomb1.equals(bomb2));
	}
}