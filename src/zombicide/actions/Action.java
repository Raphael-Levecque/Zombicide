package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of action*/
public abstract class Action {
	
	/** Cost of the action*/
	protected int cost;

	/** Constructor of the action
	 * @param cost the cost of the action
	 */
	public Action(int cost) {
		this.cost = cost;
	}
	
	/** Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public abstract boolean doable(Player p);
	
	/** Use the action
	 * @param p the player
	 */
	public abstract void use(Player p);

	/** Decrease the action points of the player
	 * @param p the player
	 */
	public void decreaseActionPoints(Player p){
		p.decreaseActionPoints(this.cost);
	}
	/**
	 * toString
	 */
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
