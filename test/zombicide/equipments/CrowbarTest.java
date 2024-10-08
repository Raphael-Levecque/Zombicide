package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Crowbar;

class CrowbarTest {

	@Test
	void creationTest() {
		Crowbar a = new Crowbar();
		assertEquals(1,a.getNumberOfThrowDice());
		assertEquals(4,a.getThreshold());
		assertEquals(1,a.getDamage());
		assertEquals(0,a.getReach());
	}
}
