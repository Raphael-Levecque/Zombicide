package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of TakeInHand */
public class TakeInHand extends Action {

	/** Constructor of the action take in hand */
	public TakeInHand() {
		super(1);
	}
	
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.canTakeInHand();
	}
	
	/**
	 * Take in hand
	 * @param p the player
	 */
	public void use(Player p) {
		p.takeFromTheBag();
		this.decreaseActionPoints(p);
	}
}
