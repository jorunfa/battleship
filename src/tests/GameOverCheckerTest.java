package tests;

import junit.framework.TestCase;

import com.example.model.Boat;
import com.example.model.Boats;
import com.example.model.Bombs;
import com.example.model.Direction;
import com.example.model.GameOverChecker;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;

public class GameOverCheckerTest extends TestCase {

	public void testThereExistsABombAtThisPositionBelongingToPlayerFunction() throws Throwable {
		Bombs bombs = new Bombs();
		Boats boats = new Boats();
		GameOverChecker gameOverChecker = new GameOverChecker(bombs, boats);
		
		Position placeABomb = new Position(1, 'j');
		Position nothing = new Position(5, 'f');
		Player player = Player.PLAYER1;
		Player notPlayer = Player.PLAYER2;
		bombs.placeBomb(placeABomb, player);
		
		assertTrue(gameOverChecker.thereExistsABombAtThisPositionFiredAtPlayer(placeABomb, player));
		assertFalse(gameOverChecker.thereExistsABombAtThisPositionFiredAtPlayer(nothing, player));
		assertFalse(gameOverChecker.thereExistsABombAtThisPositionFiredAtPlayer(placeABomb, notPlayer));
		assertFalse(gameOverChecker.thereExistsABombAtThisPositionFiredAtPlayer(nothing, notPlayer));
	}
	
	public void testBoatIsSunkFunction() throws Throwable {
		
	}
	
	public void testGameShouldBeOverWhenAllBoatsOfOnePlayerAreSunk() throws Throwable {
		GameOverChecker gameOverChecker = getGameOverGameOverCheckerWithWinner(Player.PLAYER2);
		assertTrue(gameOverChecker.gameIsOver());
	}
	
	public void testPlayerTwoShouldBeTheWinnerWhenAllPlayerOneShipsAreSunk() throws Throwable {
		GameOverChecker gameOverChecker = getGameOverGameOverCheckerWithWinner(Player.PLAYER2);
		assertSame(Player.PLAYER2, gameOverChecker.getWinner());
	}
	
	public void testPlayerOneShouldBeTheWinnerWhenAllPlayerTwoShipsAreSunk() throws Throwable {
		GameOverChecker gameOverChecker = getGameOverGameOverCheckerWithWinner(Player.PLAYER1);
		assertSame(Player.PLAYER1, gameOverChecker.getWinner());
	}
	
	public void testGameShouldNotBeOverIfNotAllShipsOfOnePlayerAreBombed() throws Throwable {
		Bombs bombs = new Bombs();
		Boats boats = new Boats();
		GameOverChecker gameOverChecker = new GameOverChecker(bombs, boats);
		assertFalse(gameOverChecker.gameIsOver());
	}

	private GameOverChecker getGameOverGameOverCheckerWithWinner(Player player) {
		Bombs bombs = new Bombs();
		Boats boats = new Boats();
		GameOverChecker gameOverChecker = new GameOverChecker(bombs, boats);
		Position pos = new Position(1, 'j');
		Direction direction = Direction.RIGHT;
		Orientation orientation = new Orientation(pos, direction);
		
		for (Boat boat : boats.getBoats()) {
			boat.placeBoat(orientation);
		}
		
		Player toBombPlayer;
		if (player == Player.PLAYER1) toBombPlayer = Player.PLAYER2;
		else toBombPlayer = Player.PLAYER1;
		
		for (int i = 1; i < 10; i++) {
			pos = new Position(i, 'j');
			bombs.placeBomb(pos, toBombPlayer);
		}
		
		return gameOverChecker;
	}
	
	
}