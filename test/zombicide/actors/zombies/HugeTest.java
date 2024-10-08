package zombicide.actors.zombies;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.zombie.Abomination;
import zombicide.actors.zombie.Huge;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class HugeTest {
	
	private Position p;
	private Zone z;
	private Huge h;
	private Board b;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.h = new Huge();
		z.addZombie(h);
	}
	@Test
	void creationTest() {
		assertEquals(4,h.getLifePoints());
		assertEquals(2,h.getDamage());
		assertEquals(1,h.getActionPoints());
	}
	@Test
	void takeDamageTest() {
		h.takeDamage(3);
		assertEquals(1,h.getLifePoints());
		h.takeDamage(1);
		assertEquals(1,h.getLifePoints());
		h.takeDamage(2);
		List<Zombie> emptyZombieList = new ArrayList<>();
		assertEquals(emptyZombieList,z.getZombies());
	}
}

