package tests;

import junit.framework.Assert.*;
import junit.framework.TestCase;

import com.example.model.ArrayGameModel;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Direction;
import com.example.model.ModelStage;
import com.example.model.ModelTurn;
import com.example.model.Player;
import com.example.model.Position;

public class BoatTest extends TestCase {

	public void testBoatLengthsShouldBeCorrect() throws Throwable {
		Boat aircraft_carrier = new Boat(BoatType.AIRCRAFT_CARRIER, null);
		Boat battleship = new Boat(BoatType.BATTLESHIP, null);
		Boat submarine = new Boat(BoatType.SUBMARINE, null);
		Boat destroyer = new Boat(BoatType.DESTROYER, null);
		Boat patrol_boat = new Boat(BoatType.PATROL_BOAT, null);
		
		assertEquals(5, aircraft_carrier.getLength());
		assertEquals(4, battleship.getLength());
		assertEquals(3, submarine.getLength());
		assertEquals(3, destroyer.getLength());
		assertEquals(2, patrol_boat.getLength());
	}
}