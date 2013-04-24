package tests;

import junit.framework.TestCase;

import com.example.model.Boat;
import com.example.model.BoatType;
import com.example.model.Direction;
import com.example.model.ModelImplementation;
import com.example.model.Orientation;
import com.example.model.Player;
import com.example.model.Position;

public class BoatCollisionCheckerTest extends TestCase {

	public void testLegalPlacementOfBoatShouldReturnFalseIfPlacingABoatOnTopOfAnother() throws Throwable {
        ModelImplementation model = new ModelImplementation();
        Position pos = new Position(5, 'e');
        Direction direction = Direction.RIGHT;
        Orientation orientation = new Orientation(pos, direction);
        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
        testBoat.placeBoat(orientation);
        
        Boat secondBoat = model.getBoat(BoatType.BATTLESHIP, Player.PLAYER1);
        
        assertFalse(model.legalPlacementOfBoat(secondBoat, orientation));
    }
	
	public void testLegalPlacementOfBoatShouldReturnTrueWhenPlacingTwoBoatsLegally() throws Throwable {
		ModelImplementation model = new ModelImplementation();
        Position positionBoat1 = new Position(5, 'e');
        Direction right = Direction.RIGHT;
        Orientation orientationBoat1 = new Orientation(positionBoat1, right);

        Position positionBoat2 = new Position(1, 'j');
        Orientation orientationBoat2 = new Orientation(positionBoat2, right);
        
        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
        testBoat.placeBoat(orientationBoat1);
        
        Boat secondBoat = model.getBoat(BoatType.BATTLESHIP, Player.PLAYER1);
        
        assertTrue(model.legalPlacementOfBoat(secondBoat, orientationBoat2));
    }
	
	public void testLegalPlacementOfBoatShouldReturnFalseIfPlacingABoatAndTheyCrossEachother() throws Throwable {
		ModelImplementation model = new ModelImplementation();
        Position pos1 = new Position(5, 'e');
        Position pos2 = new Position(6, 'f');
        Direction direction1 = Direction.RIGHT;
        Direction direction2 = Direction.UP;
        Orientation orientation1 = new Orientation(pos1, direction1);
        Orientation orientation2 = new Orientation(pos2, direction2);
        Boat testBoat = model.getBoat(BoatType.AIRCRAFT_CARRIER, Player.PLAYER1);
        testBoat.placeBoat(orientation1);
        
        Boat secondBoat = model.getBoat(BoatType.BATTLESHIP, Player.PLAYER1);
        
        assertFalse(model.legalPlacementOfBoat(secondBoat, orientation2));
    }
	
}