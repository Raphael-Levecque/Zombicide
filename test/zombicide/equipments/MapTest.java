package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.board.Board;
import zombicide.board.TrainingBoard;

class MapTest {

	@Test
	void creationTest() {
		Board b = new TrainingBoard();
		Map m = new Map(b);
		assertFalse(m.isAbleToOpenADoor());
	}

}
