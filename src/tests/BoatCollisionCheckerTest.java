package tests;

import junit.framework.TestCase;

import com.example.model.ArrayGameModel;
import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Direction;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;

public class BoatCollisionCheckerTest extends TestCase {

	public void testLegalPlacementOfBoatShouldReturnFalseIfPlacingABoatOnTopOfAnother() throws Throwable {
        ArrayGameModel model = new ArrayGameModel();
        Position pos = new Position(5, 'e');
        Direction direction = Direction.RIGHT;
        Orientation orientation = new Orientation(pos, direction);
        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
        testBoat.placeBoat(orientation);
        
        Boat secondBoat = model.getBoat(BoatType.BATTLESHIP, Player.PLAYER1);
        
        assertFalse(model.legalPlacementOfBoat(secondBoat, orientation));
    }
	
}