package zombicide.equipments;

/** Class of BottleOfHealing */
public class BottleOfHealing extends Heal{

	/** heal point of the fiole */
	private static int healPoint = 1;

	/** Constructor of BottleOfHealing */
	public BottleOfHealing() {
		super(healPoint);
	}

	/**
	 * use the fiole to heal a player
	 */
	public void use() {
		this.player.setLifePoints(this.player.getLifePoints() + healPoint);
		System.out.println("You have been healed with a fiole of " + healPoint + " points");
	}
	
}
