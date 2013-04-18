package tests;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.example.model.ArrayGameModel;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Button;
import com.example.model.Direction;
import com.example.model.ModelStage;
import com.example.model.ModelTurn;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;

public class ModelTest extends TestCase {

	public void testShouldBePlayer1sTurnAndPlaceBoatStageWhenStartingNewGame() throws Throwable {
        ArrayGameModel model = new ArrayGameModel();
        assertEquals(ModelTurn.PLAYER1, model.getTurn());
        assertEquals(ModelStage.PLACE_BOATS, model.getStage());   
    }
	
	public void testAllBoatsShouldBeCreatedInTheStart() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		assertEquals(BoatType.AIRCRAFT_CARRIER, model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1).getType());
		assertEquals(BoatType.BATTLESHIP, model.getBoat(BoatType.BATTLESHIP, Player.PLAYER1).getType());
		assertEquals(BoatType.SUBMARINE, model.getBoat(BoatType.SUBMARINE, Player.PLAYER1).getType());
		assertEquals(BoatType.DESTROYER, model.getBoat(BoatType.DESTROYER, Player.PLAYER1).getType());
		assertEquals(BoatType.PATROL_BOAT, model.getBoat(BoatType.PATROL_BOAT, Player.PLAYER1).getType());
		
		assertEquals(BoatType.AIRCRAFT_CARRIER, model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER2).getType());
		assertEquals(BoatType.BATTLESHIP, model.getBoat(BoatType.BATTLESHIP, Player.PLAYER2).getType());
		assertEquals(BoatType.SUBMARINE, model.getBoat(BoatType.SUBMARINE, Player.PLAYER2).getType());
		assertEquals(BoatType.DESTROYER, model.getBoat(BoatType.DESTROYER, Player.PLAYER2).getType());
		assertEquals(BoatType.PATROL_BOAT, model.getBoat(BoatType.PATROL_BOAT, Player.PLAYER2).getType());
	}
	
	public void testThereShouldBeNoBoatsPlacedInTheStart() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		for (Boat boat : model.getBoats()) {
			assertSame(null, boat.getOrientation());
		}
	}
	
	public void testAttemptToPlaceBoatShouldWork() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		Boat boat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
		Position pos = new Position(5, 'e');
		Orientation orientation = new Orientation(pos, Direction.RIGHT);
		model.attemptToPlaceBoat(boat, orientation);
		assertTrue(boat.isPlaced());
	}
	
	public void testUpdatingWithChangeDirectionButtonShouldUpdateTheModel() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		Button button = Button.ChangeDirection;
		Direction originalDirection = model.getDirection();
		
		model.update(null, button);
		
		Direction maybeChangedDirection = model.getDirection();
		assertNotSame(originalDirection, maybeChangedDirection);
	}
}