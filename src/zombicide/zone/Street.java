package zombicide.zone;

import zombicide.board.Board;
import zombicide.equipments.Equipment;

/** Class of Street */
public class Street extends Zone{
	
	
	/**
	 * Builds a Street with given position
	 * @param p the position
	 * @param b the board
	 */
	public Street(Position p,Board b) {
		super(p,b);
		this.name = "S";
		this.searchable=false;
	}

	/**
	 * Add an equipment
	 * @param equip the equipment to add
	 */
	public void addEquipment(Equipment equip) {
	}
	
}
