package zombicide.actions;

import zombicide.actors.players.Player;
import zombicide.exception.NoSuchZoneException;

/** Class of Move */
public class Move extends Action{

	/** Constructor of the action move */
	public Move() {
		super(1);
	}
	
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.canMove();
	}
	
	/**
	 * Move the player
	 * @param p the player
	 */
	public void use(Player p) {
		try {
			p.move();
			this.decreaseActionPoints(p);
		}
		catch (NoSuchZoneException e) {
			e.getStackTrace();
		}
	}
	
}
