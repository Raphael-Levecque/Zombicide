package zombicide.zone;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.zone.Position;

class PositionTest {
	
	private Position pos1;
	private Position possame;
	private Position pos2;
	private Position pos3;
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.pos1 = new Position(3,5);
		this.possame = new Position(3,5);
		this.pos2 = new Position(3,6);
		this.pos3 = new Position(4,5);
	}
	
	@Test
	public void initializationIsOk() {
	    assertEquals(3, pos1.getX());
	    assertEquals(5, pos1.getY());
	}
	
	@Test
	public void testEqualsWhenXAndYAreEquals() {
	    assertTrue(pos1.equals(possame));      
	}
	
	@Test
	public void testEqualsWhenYAreNotEquals() {
	    assertFalse(pos1.equals(pos2));      
	}
	
	@Test
	public void testEqualsWhenXAreNotEquals() {
	    assertFalse(pos1.equals(pos3));      
	}
	
	@Test
	public void testEqualsWhenXAndYAreNotEquals() {
		Position pos4 = new Position(3,6);
	    assertFalse(pos4.equals(pos3));      
	}
	
	@Test
	public void testPositionToString() {
		assertEquals("(3,5)",pos1.toString());
	}
	




}
