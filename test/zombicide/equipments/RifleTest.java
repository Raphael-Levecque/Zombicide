package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Rifle;

class RifleTest {

	@Test
	void creationTest() {
		Rifle a = new Rifle();
		assertEquals(1,a.getNumberOfThrowDice());
		assertEquals(4,a.getThreshold());
		assertEquals(1,a.getDamage());
		assertEquals(3,a.getReach());
		assertEquals(1,a.getMinimalReach());
	}

}
