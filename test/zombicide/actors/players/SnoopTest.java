package zombicide.actors.players;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Snoop;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

class SnoopTest {

	@Test
	void creationTest() {
		Snoop c = new Snoop();
		assertEquals(5,c.getLifePoints());
		assertEquals(3,c.getActionPoints());
	}

}
