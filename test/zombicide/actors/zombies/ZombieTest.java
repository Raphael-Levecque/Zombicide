package zombicide.actors.zombies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Lucky;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class ZombieTest {
	
	private Position p;
	private Zone z;
	private Zombie zomb;
	private Board b;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.p= new Position(1,2);
		this.z = new Zone(p,b);
		this.zomb = new Zombie(2,1,3);
		this.b = new TrainingBoard();
		z.addZombie(zomb);
	}
	
	
	@Test
	void attackTest() {
		Lucky chan = new Lucky();
		z.addPlayer(chan);
		assertEquals(5,chan.getLifePoints());
		zomb.attack();
		assertEquals(4,chan.getLifePoints());
	}
	@Test
	void takeDamageTest() {
		assertEquals(2,zomb.getLifePoints());
		zomb.takeDamage(1);
		assertEquals(1,zomb.getLifePoints());
	}
}
