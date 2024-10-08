package zombicide.actions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Fighter;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.util.CardinalPoints;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;

public class LookAroundTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private LookAround lookAround;
	private Door door;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.lookAround = new LookAround();
		this.door = new Door(true);
	}
	@Test
	public void useTest() {
		z.addPlayer(c);
		z.addDoor(CardinalPoints.NORTH, door);
		z.addDoor(CardinalPoints.EAST, door);
		z.addDoor(CardinalPoints.SOUTH, door);
		z.addDoor(CardinalPoints.WEST, door);
		lookAround.use(c);
		assertEquals(3, c.getCurrentActionPoints());
	}
}
