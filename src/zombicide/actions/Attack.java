package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of action attack */
public class Attack extends Action{
    
    /** The cost of the action */
	protected int cost ;

	/** Constructor of the action attack */
	public Attack() {
		super(1);
	}
	
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.canAttack();
	}
	
	/**
	 * Attack a zombie
	 * @param p the player
	 */
	public void use(Player p) {
		p.attack();
        this.decreaseActionPoints(p);
	}
}
