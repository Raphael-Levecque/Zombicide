package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Chainsaw;

class ChainsowTest {

	@Test
	void creationTest() {
		Chainsaw a = new Chainsaw();
		assertEquals(1,a.getNumberOfThrowDice());
		assertEquals(5,a.getThreshold());
		assertEquals(3,a.getDamage());
		assertEquals(0,a.getReach());
	}

}
