package zombicide.zone.doors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.equipments.MasterKey;
import zombicide.equipments.Rifle;
import zombicide.zone.doors.Door;

class DoorTest {
	
	private Door d;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.d = new Door(true);
	}
	
	@Test
	public void isOpenTest() {
		assertFalse(d.isOpen());
	}
	@Test
	public void openIsPossibleTest() {
		assertFalse(d.isOpen());
		d.open();
		assertTrue(d.isOpen());
	}
	@Test
	public void openIsNotPossibleTest() {
		Door donk = new Door(false);
		assertFalse(donk.isOpen());
		donk.open();
		assertFalse(donk.isOpen());
	}
	@Test
	public void openWithASpecificEquipmentTest(){
		Rifle r = new Rifle();
		d.openWith(r);
		assertEquals(false ,d.isOpen());
		MasterKey m = new MasterKey();
		d.openWith(m);
		assertEquals(true ,d.isOpen());
	}
}
