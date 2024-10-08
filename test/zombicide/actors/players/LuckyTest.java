package zombicide.actors.players;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Lucky;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class LuckyTest {

	@Test
	void creationTest() {
		Lucky c = new Lucky();
		assertEquals(5,c.getLifePoints());
		assertEquals(3,c.getActionPoints());
	}

}
