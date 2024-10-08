package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;

class InfraredGlassesTest {

	@Test
	void creationTest() {
		InfraredGlasses i = new InfraredGlasses();
		assertFalse(i.isAbleToOpenADoor());
	}

}
