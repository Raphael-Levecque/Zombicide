package zombicide.actions;



import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import zombicide.actors.players.Fighter;
import zombicide.actors.players.Snoop;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.equipments.Pistol;
import zombicide.equipments.Rifle;
import zombicide.zone.Position;
import zombicide.zone.Street;
import zombicide.zone.Zone;

public class SearchTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private Axe a;
	private Rifle r;
	private Position p2;
	private Street street;
	private Fighter c2;
	private Zone z2;
	private Search search;
	private Snoop snoop;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.a = new Axe();
		this.r = new Rifle();
		this.p2 = new Position(2,2);
		this.street = new Street(p2,b);
		this.z2 = new Zone(p2,b);
		this.c2 = new Fighter();
		this.search = new Search();
		this.snoop = new Snoop();
	}
	@Test
	public void doableTest() {
		z.addPlayer(c);
		z.addEquipment(new Pistol());
		street.addPlayer(c2);
		assertTrue(search.doable(c));
		assertFalse(search.doable(c2));
	}
	@Test
	public void useTest() {
		z.addPlayer(snoop);
		z.addPlayer(c);
		z.addEquipment(a);
		z.addEquipment(r);
		search.use(c);
		assertEquals(2, c.getCurrentActionPoints());
		search.use(snoop);
		assertEquals(3, snoop.getCurrentActionPoints());
	}
}
