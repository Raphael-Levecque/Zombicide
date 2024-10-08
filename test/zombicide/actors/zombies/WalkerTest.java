package zombicide.actors.zombies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.zombie.Walker;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class WalkerTest {

	@Test
	void creationTest() {
		Walker w = new Walker();
		assertEquals(1,w.getLifePoints());
		assertEquals(1,w.getDamage());
		assertEquals(1,w.getActionPoints());
	}

}
