package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import zombicide.actions.OpenDoor;
import zombicide.actors.players.Lucky;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.util.CardinalPoints;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;
import zombicide.actors.zombie.*;

class MasterKeyTest {

	@Test
	void test() {
		MasterKey m = new MasterKey();
		assertTrue(m.isAbleToOpenADoor());
	}
	void useTest() {
		Board b = new TrainingBoard();
		OpenDoor openDoor = new OpenDoor(b);
		Position position = new Position(0,0);
		Zone zone = new Zone(position,b);
		Lucky lucky = new Lucky();
		zone.addPlayer(lucky);
		MasterKey a = new MasterKey();
		MasterKey bb = new MasterKey();
		MasterKey c = new MasterKey();
		MasterKey d = new MasterKey();
		Door door = new Door(true);
		CardinalPoints east = CardinalPoints.EAST;
		zone.addDoor(east, door);
		CardinalPoints north = CardinalPoints.NORTH;
		zone.addDoor(north, door);
		CardinalPoints south = CardinalPoints.SOUTH;
		zone.addDoor(south, door);
		CardinalPoints west = CardinalPoints.WEST;
		zone.addDoor(west, door);
		lucky.setInhand(a);
		openDoor.use(lucky);
		lucky.setInhand(bb);
		openDoor.use(lucky);
		lucky.setInhand(c);
		openDoor.use(lucky);
		lucky.setInhand(d);
		openDoor.use(lucky);
		
		assertEquals(0, zone.getClosedAndOpenableDoors().size());
	}
}
