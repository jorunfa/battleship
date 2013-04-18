package tests;

import junit.framework.TestCase;

import com.example.model.ArrayGameModel;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Button;
import com.example.model.Direction;
import com.example.model.Stage;
import com.example.model.Turn;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;

public class ModelTest extends TestCase {

	public void testShouldBePlayer1sTurnAndPlaceBoatStageWhenStartingNewGame() throws Throwable {
        ArrayGameModel model = new ArrayGameModel();
        assertEquals(Turn.PLAYER1, model.getTurn());
        assertEquals(Stage.PLACE_BOATS, model.getStage());   
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
		Button button = Button.CHANGE_DIRECTION;
		Direction originalDirection = model.getDirection();
		
		model.update(null, button);
		
		Direction maybeChangedDirection = model.getDirection();
		assertNotSame(originalDirection, maybeChangedDirection);
	}
	
	public void testGetNextBoatToPlaceShouldReturnNextBoatWhenBoatsArePlaced() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		Position pos = new Position(5, 'e');
		Orientation orientation = new Orientation(pos, Direction.RIGHT);
		Boat boatToPlace;
		while (model.getNextBoatToPlace() != null) {
			boatToPlace = model.getNextBoatToPlace();
			boatToPlace.placeBoat(orientation);
			assertNotSame(boatToPlace, model.getNextBoatToPlace());
		}
	}
	
	public void testButtonChangingPlayersPauseScreenUpdateShouldSetShowChangingPlayersScreenFalse() {
		ArrayGameModel model = new ArrayGameModel();
		model.update(null, Button.CHANGING_PLAYERS_PAUSESCREEN_NEXT);
		assertFalse(model.showChangingPlayersScreen());
	}
	
	public void testUpdateWithAPositionShouldWorkAccordingToCurrentState() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		Position pos1 = new Position(1, 'a');
		model.update(null, pos1);
		Boat boat1 = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
		assertTrue(boat1.isPlaced());
		
		Position pos2 = new Position(5, 'e');
		model.update(null, pos2);
		Boat boat2 = model.getBoat(BoatType.BATTLESHIP, Player.PLAYER1);
		assertTrue(boat2.isPlaced());
	}
	
	public void testPlacingThroughUpdateAllPlayerOneBoatsShouldChangeTurnToPlayerTwo() throws Throwable {
		ArrayGameModel model = new ArrayGameModel();
		sendUpdateOnFiveDifferentPlacesOnGrid(model);
		assertEquals(Turn.PLAYER2, model.getTurn());
	}

	private void sendUpdateOnFiveDifferentPlacesOnGrid(ArrayGameModel model) {
		Position p1 = new Position(1, 'j');
		Position p2 = new Position(2, 'i');
		Position p3 = new Position(3, 'h');
		Position p4 = new Position(4, 'g');
		Position p5 = new Position(5, 'f');
		model.update(null, p1);
		model.update(null, p2);
		model.update(null, p3);
		model.update(null, p4);
		model.update(null, p5);
	}
}