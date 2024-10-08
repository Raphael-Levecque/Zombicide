package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of Look>Around */
public class LookAround extends Action {
	
	/** The cost of the action */
	protected int cost ;

	/** Constructor of the action look around */
	public LookAround() {
		super(0);
	}
	
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return true;
	}
	
	/**
	 * Look around the player
	 * @param p the player
	 */
	public void use(Player p) {
		p.lookAround();
		this.decreaseActionPoints(p);
	}
}
