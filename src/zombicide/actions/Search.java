package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of Search */
public class Search extends Action {
	
	/** Constructor of the action search */ 
	public Search() {
		super(1);
	}

	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.canSearch();
	}
	
	/**
	 * Search the zone
	 * @param p the player
	 */
	public void use(Player p) {
		p.search();
		this.decreaseActionPoints(p);
	}
	
	/**
	 * Decrease the action points of the player
	 * @param p the player
	 */
	public void decreaseActionPoints(Player p){
		if(p.isSnoop()) {
			p.decreaseActionPoints(0);
		}
		else {
			p.decreaseActionPoints(this.cost);
		}
	} 

}
