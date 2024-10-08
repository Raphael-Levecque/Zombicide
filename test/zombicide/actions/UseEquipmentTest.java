package zombicide.actions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Fighter;
import zombicide.actors.zombie.Walker;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.equipments.MasterKey;
import zombicide.equipments.Rifle;
import zombicide.util.CardinalPoints;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;
import zombicide.actions.*;

class UseEquipmentTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private MasterKey a;
	private Rifle r;
	private UseEquipment u;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.a = new MasterKey();
		this.r = new Rifle();
		this.u = new UseEquipment();
		z.addPlayer(c);
	}
	@Test
	void doableTest() {
		c.setInhand(a);
		assertTrue(u.doable(c));
		c.drop(a);
		c.setInhand(r);
		assertFalse(u.doable(c));
	}
	@Test
	void useTest() {
		c.setInhand(a);
		a.setPlayer(c);
		CardinalPoints north = CardinalPoints.NORTH;
		CardinalPoints south = CardinalPoints.SOUTH;
		CardinalPoints west = CardinalPoints.WEST;
		CardinalPoints east = CardinalPoints.EAST;
		Door openable = new Door(true);
		Door notOpenable = new Door(false);
		z.addDoor(north, openable);
		z.addDoor(east, notOpenable);
		z.addDoor(west, notOpenable);
		z.addDoor(south, notOpenable);
		u.use(c);
		assertEquals(2, c.getCurrentActionPoints());
		assertTrue(z.isDoorOpen(north));
	}
	
}
