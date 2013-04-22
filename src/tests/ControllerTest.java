package tests;

import com.example.controller.TouchController;
import com.example.model.ArrayGameModel;
import com.example.model.ModelStage;
import com.example.model.ModelTurn;
import com.example.model.Position;
import com.example.starter.SurfaceViewActivity;

import junit.framework.TestCase;

public class ControllerTest extends TestCase {

	public void testReturnCoord() throws Throwable {
		
		Position aPos = new Position(3, 'b');
		TouchController aCont = new TouchController();

		assertEquals(aPos, aCont.returnCoord(33, 20));
	}

}
