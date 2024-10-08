package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Lucky;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.BottleOfHealing;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.actions.*;

class BottleOfHealingTest {
	
	private BottleOfHealing aid;
	private UseEquipment useEquipment;
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.aid= new BottleOfHealing();
		this.useEquipment = new UseEquipment();
	}
	
	@Test
	void creationTest() {
		assertEquals(1,aid.getHealValue());
	}
	void useTest() {
		Board b = new TrainingBoard();
		Position position = new Position(0,0);
		Zone zone = new Zone(position,b);
		Lucky lucky = new Lucky();
		zone.addPlayer(lucky);
		lucky.takeDamage(2);
		lucky.setInhand(aid);
		assertEquals(3,lucky.getLifePoints());
		useEquipment.use(lucky);
		assertEquals(4,lucky.getLifePoints());
	}

}
