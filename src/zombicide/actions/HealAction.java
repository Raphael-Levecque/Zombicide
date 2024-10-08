package zombicide.actions;

import zombicide.actors.players.Player;

public class HealAction extends Action {
	/**
	 * builds a HealAction
	 */
	public HealAction() {
		super(1);
	}
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.isHealer();
	}
	/**
	 * heal a player
	 */
	public void use(Player p) {
		p.heal();
		this.decreaseActionPoints(p);
	}
	
	

}
