package zombicide.actors.zombie;

/** Class of Huge */
public class Huge extends Zombie {
	/**
	 * Builds a Huge 
	 */
	public Huge() {
		super(4,2,1);
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
