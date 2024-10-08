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

class AbominationTest {
	
	private Position p;
	private Zone z;
	private Abomination a;
	private Board b;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.a = new Abomination();
		z.addZombie(a);
	}
	@Test
	void creationTest() {
		assertEquals(6,a.getLifePoints());
		assertEquals(3,a.getDamage());
		assertEquals(1,a.getActionPoints());
	}
	@Test
	void takeDamageTest() {
		a.takeDamage(5);
		assertEquals(1,a.getLifePoints());
		a.takeDamage(1);
		assertEquals(1,a.getLifePoints());
		a.takeDamage(2);
		List<Zombie> emptyZombieList = new ArrayList<>();
		assertEquals(emptyZombieList,z.getZombies());
	}
}
