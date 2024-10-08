package zombicide.actors.players;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actions.*;
import zombicide.actors.players.Fighter;
import zombicide.actors.zombie.Walker;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class FighterTest {

	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private Axe a;
	private Attack att;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.a = new Axe();
		this.att = new Attack();
	}
	
	@Test
	void creationTest() {
		assertEquals(5,c.getLifePoints());
		assertEquals(3,c.getActionPoints());
	}
	@Test
	void attackTest() {
		Walker w = new Walker();
		z.addZombie(w);
		z.addPlayer(c);
		a.setPlayer(c);
		c.setInhand(a);
		att.use(c);
		assertEquals(1, c.getThrowThimbleBonus());
	}
}
