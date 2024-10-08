package zombicide.actors.players;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Healer;
import zombicide.actors.zombie.Huge;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class HealerTest {
	
	private Position p;
	private Zone z;
	private Healer healer;
	private Board b;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.healer = new Healer();
		z.addPlayer(healer);
	}
	
	@Test
	void creationTest() {
		assertEquals(5,healer.getLifePoints());
		assertEquals(3,healer.getActionPoints());
	}
	@Test
	void healTest() {
		healer.heal();
		assertEquals(6, healer.getLifePoints());
	}

}
