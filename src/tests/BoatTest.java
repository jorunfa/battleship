package tests;

import junit.framework.TestCase;

import com.example.model.ArrayGameModel;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Direction;
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
	
	public void testPlacingABoatShouldPlaceABoatInTheModel() throws Throwable {
        ArrayGameModel model = new ArrayGameModel();
        Position pos = new Position(5, 'e');
        Direction direction = Direction.RIGHT;
        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
        testBoat.placeBoat(pos, direction);
        
        assertEquals(pos, testBoat.getPosition());
        assertEquals(direction, testBoat.getDirection());
    }
	
	public void testLegalPlacementOfBoatShouldReturnFalseIfPlacingABoatAtTheRightEdgeOfTheGridAndBoatDirectionIsRightBecauseOfSpaceLimitations()
		throws Throwable {
			ArrayGameModel model = new ArrayGameModel();
	        Position pos = new Position(10, 'e');
	        Direction direction = Direction.RIGHT;
	        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
	        assertFalse(testBoat.legalPlacementOfBoat(pos, direction));
		}
	
	public void testLegalPlacementOfBoatShouldReturnFalseIfPlacingABoatAtTheTopOfTheGridAndBoatDirectionIsUpBecauseOfSpaceLimitations()
		throws Throwable {
			ArrayGameModel model = new ArrayGameModel();
	        Position pos = new Position(10, 'a');
	        Direction direction = Direction.UP;
	        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
	        assertFalse(testBoat.legalPlacementOfBoat(pos, direction));
		}
}