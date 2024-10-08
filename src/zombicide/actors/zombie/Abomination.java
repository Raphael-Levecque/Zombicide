package zombicide.actors.zombie;

/** Class of Abomination */
public class Abomination extends Zombie {
	/**
	 * Builds a Abomination 
	 */
	public Abomination() {
		super(6,3,1);
	}
	
	/**
	 * decrease the life points of damage if damage > 1
	 * and analyze if the zombie is still alive, if not removes it
	 * @param damage the damage the zombie took
	 */
	public boolean takeDamage(int damage){
		if (damage > 1) {
			return super.takeDamage(damage);
		}
		else {
			return false;
		}
	}

}
