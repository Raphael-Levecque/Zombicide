package zombicide.actions;

import zombicide.actors.players.Player;

/** Class of UseEquipment */
public class UseEquipment extends Action {
	
	/** Constructor of the action use equipment */
	public UseEquipment() {
		super(1);
	}
	
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.canUseEquipment();
	}
	
	/**
	 * Use the equipment
	 * @param p the player
	 */
	public void use(Player p) {
		p.useEquipment();
		this.decreaseActionPoints(p);
	}
}
