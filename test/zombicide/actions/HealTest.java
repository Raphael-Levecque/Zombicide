package zombicide.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.*;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

public class HealTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private HealAction healAction;
	private Healer healer;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.healAction = new HealAction();
		this.healer = new Healer();
	}
	@Test
	public void doableTest() {
		assertFalse(healAction.doable(c));
		assertTrue(healAction.doable(healer));
	}
	@Test 
	public void useTest(){
		z.addPlayer(healer);
		healAction.use(healer);
		assertEquals(6, healer.getLifePoints());
		assertEquals(2,healer.getCurrentActionPoints());
	}
}
