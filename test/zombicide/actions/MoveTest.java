package zombicide.actions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import zombicide.actors.players.Fighter;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.equipments.Rifle;
import zombicide.exception.NoSuchZoneException;
import zombicide.util.CardinalPoints;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;

public class MoveTest {
	private Position p;
	private Board b;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
	}
	@Test
	public void doableTest() {
		Position pos = new Position(1,1);
		Board board = new TrainingBoard();
		Zone test = new Zone(pos,board);
		Fighter play = new Fighter();
		Move mov = new Move();
		test.addPlayer(play);
		assertFalse(mov.doable(play));
		Door opDoor = new Door(true);
		test.addDoor(CardinalPoints.NORTH, opDoor);
		test.openDoor(CardinalPoints.NORTH);
		assertTrue(mov.doable(play));
	}
	@Test 
	public void useTest(){
		Position pos = new Position(1,1);
		Board board = new TrainingBoard();
		Zone test = new Zone(pos,board);
		Fighter play = new Fighter();
		test.addPlayer(play);
		Move mov = new Move();
		Door opDoor = new Door(true);
		Position pos2 = new Position(0,1);
		Zone zone2 = new Zone(pos2,board);
		test.addNeighbour(zone2, CardinalPoints.NORTH);
		test.addDoor(CardinalPoints.NORTH, opDoor);
		test.openDoor(CardinalPoints.NORTH);
		mov.use(play);
		assertEquals(play.getZone().getPosition(),zone2.getPosition());
		assertEquals(2,play.getCurrentActionPoints());
	}
	@Test
	public void useInAnUnexistingZoneTest() throws NoSuchZoneException{
		Position p3 = new Position(0,0);
		Zone z3 = new Zone(p3,b);
		Door openable = new Door(true);
		Fighter play = new Fighter();
		z3.addPlayer(play);
		z3.addDoor(CardinalPoints.NORTH, openable);
		z3.openDoor(CardinalPoints.NORTH);
		assertThrows(NoSuchZoneException.class, () -> z3.getNeighbour(CardinalPoints.NORTH));
	}
}
