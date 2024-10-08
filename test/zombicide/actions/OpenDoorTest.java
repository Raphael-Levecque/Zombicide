package zombicide.actions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Fighter;
import zombicide.actors.players.Snoop;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.equipments.Equipment;
import zombicide.equipments.Rifle;
import zombicide.util.CardinalPoints;
import zombicide.zone.Continentale;
import zombicide.zone.Position;
import zombicide.zone.Street;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;

public class OpenDoorTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private Axe a;
	private Rifle r;
	private OpenDoor open;
	private Door openableDoor;
	private CardinalPoints north;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.a = new Axe();
		this.r = new Rifle();
		this.open = new OpenDoor(b);
		this.openableDoor = new Door(true);
		this.north = CardinalPoints.NORTH;
		z.addPlayer(c);
	}
	@Test
	void doableTest() {
		c.setInhand(a);
		assertFalse(open.doable(c));
		z.addDoor(north, openableDoor);
		assertTrue(open.doable(c));
		c.drop(a);
		c.setInhand(r);
		assertFalse(open.doable(c));
	}
	@Test
	void useTest() {
		c.setInhand(a);
		z.addDoor(north, openableDoor);
		open.use(c);
		assertEquals(z.getClosedAndOpenableDoors().size(), 0);
		assertEquals(c.getCurrentActionPoints(), 2);
	}
}
