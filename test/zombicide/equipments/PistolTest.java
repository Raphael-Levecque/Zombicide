package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Pistol;

class PistolTest {

	@Test
	void creationTest() {
		Pistol a = new Pistol();
		assertEquals(1,a.getNumberOfThrowDice());
		assertEquals(4,a.getThreshold());
		assertEquals(1,a.getDamage());
		assertEquals(1,a.getReach());
	}

}
