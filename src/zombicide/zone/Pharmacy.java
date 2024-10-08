package zombicide.zone;

import zombicide.actors.players.Player;
import zombicide.board.Board;
import zombicide.equipments.BottleOfHealing;

/** Class of Pharmacy */
public class Pharmacy extends Building {
	

	/**
	 * builds a pharmacy with given parameters
	 * @param p the position
	 * @param b the board
	 */
	public Pharmacy(Position p,Board b) {
		super(p,b);
		this.name="P";
		}
	
	/**
	 * add player
	 * @param p the player to be added
	 */
	public void addPlayer(Player p) {
		super.addPlayer(p);
		this.addEquipement(new BottleOfHealing());
	}
}
