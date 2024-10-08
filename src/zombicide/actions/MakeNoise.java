package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of MakeNoise */
public class MakeNoise extends Action {

	/** Constructor of the action make noise */
	public MakeNoise() {
		super(1);
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
	 * Make noise
	 * @param p the player
	 */
	public void use(Player p) {
		p.makeNoise();
		this.decreaseActionPoints(p);
	}
}
