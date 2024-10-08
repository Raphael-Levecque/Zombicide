package zombicide.equipments;

import zombicide.zone.doors.Door;

/** Class of MasterKey */
public class MasterKey extends Utilities{

	/** Constructor of MasterKey */
	public MasterKey() {
		super(true);
	}

	/**
	 * open a door
	 */
	public void use() {
		Door door = this.player.chooseDoor(this.player.getClosedAndOpenableDoors());
		if(door == null) {
			System.out.println("There is no door to open");
		}
		else {
			door.open();
		}
	}
		
}



