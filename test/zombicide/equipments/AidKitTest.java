package zombicide.equipments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.equipments.AidKit;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.actors.players.Lucky;
import zombicide.actors.players.Player;
import zombicide.board.*;
import zombicide.actions.*;
class AidKitTest {
	
	private Board b;
	private AidKit aid;
	private UseEquipment useEquipment;
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.aid = new AidKit();
		this.useEquipment = new UseEquipment();
	}
	
	@Test
	void creationTest() {
		AidKit a = new AidKit();
		assertEquals(1,a.getHealValue());
	}
	void useTest() {
		Position position = new Position(0,0);
		Zone zone = new Zone(position,b);
		Lucky lucky = new Lucky();
		zone.addPlayer(lucky);
		lucky.setInhand(aid);
		lucky.takeDamage(2);
		assertEquals(3,lucky.getLifePoints());
		useEquipment.use(lucky);
		assertEquals(4,lucky.getLifePoints());
	}

}
