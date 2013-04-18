package tests;

import junit.framework.TestCase;

import com.example.model.Position;

public class PositionTest extends TestCase {

		public void testIfPositionIsOutOfBound() throws Throwable {
	          try {
	        	  Position pos = new Position(11, 'g');
	        	  fail("Exptected exeption");
			} catch (Exception e) {
				assertEquals("IndexOutOfBoundsException", e.getMessage());
			}
	    }
		
		public void testIfPositionIsNotOutOfBound() throws Throwable  {
			try {
				Position pos = new Position(5, 'e');
				assertEquals(5, pos.getColumn());
				assertEquals('e', pos.getRow());
			} catch (Exception e) {
				fail("Shuld not throw exeption");
			}
		}
	
}
