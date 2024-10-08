package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.equipments.Axe;
import zombicide.board.*;

class AxeTest {

	@Test
	void creationTest() {
		Axe a = new Axe();
		assertEquals(1,a.getNumberOfThrowDice());
		assertEquals(4,a.getThreshold());
		assertEquals(2,a.getDamage());
		assertEquals(0,a.getReach());
	}

}
