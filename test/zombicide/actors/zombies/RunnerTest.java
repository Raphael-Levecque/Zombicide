package zombicide.actors.zombies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.zombie.Runner;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class RunnerTest {

	@Test
	void creationTest() {
		Runner r = new Runner();
		assertEquals(2,r.getLifePoints());
		assertEquals(1,r.getDamage());
		assertEquals(2,r.getActionPoints());
	}

}
