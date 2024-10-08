package zombicide.equipments;

import zombicide.actors.players.Player;

/** Class of AidKit */
public class AidKit extends Heal{
	/*Attributes*/
	
	/*The amount of heal provided to a player*/
	private static int healPoint = 1;
	
	/*Methods*/
	
	
	/** Constructor of AidKit */
	public AidKit() {
		super(healPoint);
	}

	/**
	 * use the aid kit to heal a player
	 */
	public void use() {
		Player p = this.player.choosePlayer(this.player.getPlayersOfZone());
		if(p != null) {
			p.setLifePoints(p.getLifePoints()+healPoint);
			System.out.println(p + " has been healed with an aid kit of " + healPoint + " points");
		}
		else {
			System.out.println("There are no players to heal");
		}
	}
	

}
