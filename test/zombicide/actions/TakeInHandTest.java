package zombicide.actions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Fighter;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.equipments.Rifle;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.equipments.*;

public class TakeInHandTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private Axe a;
	private TakeInHand t;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.a = new Axe();
		this.t = new TakeInHand();
		z.addPlayer(c);	
		}
	@Test
	public void doableTest() {
		c.addInBag(a);
		assertTrue(t.doable(c));
		c.setInhand(a);
		assertTrue(t.doable(c));
	}
	@Test
	public void useTest() {
		c.addInBag(a);
		t.use(c);
		assertEquals( a,c.getInhand());
		assertEquals(2, c.getCurrentActionPoints());
	}
}
