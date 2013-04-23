package tests;

import junit.framework.TestCase;

import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Button;
import com.example.model.Direction;
import com.example.model.ModelImplementation;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;
import com.example.model.StateLogic;

public class StateLogicTest extends TestCase {

	public void testAttemptToPlaceBoatShouldWork() throws Throwable {
		ModelImplementation model = new ModelImplementation();
		StateLogic stateLogic = new StateLogic(model);
		Boat boat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
		Position pos = new Position(5, 'e');
		Orientation orientation = new Orientation(pos, Direction.RIGHT);
		stateLogic.attemptToPlaceBoat(boat, orientation);
		assertTrue(boat.isPlaced());
	}
	
	public void testRestartButtonShouldRestartTheGame() throws Throwable {
		ModelImplementation model = new ModelImplementation();
		StateLogic stateLogic = new StateLogic(model);
		Position aircraftCarrierPosition = new Position(1, 'j');
		Boat aircraftCarrier = stateLogic.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
		assertFalse(aircraftCarrier.isPlaced());
		stateLogic.update(null, aircraftCarrierPosition);
		assertTrue(aircraftCarrier.isPlaced());
		
		stateLogic.update(null, Button.RESTART);
		
		Boat freshAircraftCarrier = stateLogic.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
		assertFalse(freshAircraftCarrier.isPlaced());
	}
}