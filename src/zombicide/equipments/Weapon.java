package zombicide.equipments;

import java.util.ArrayList;
import java.util.List;

import zombicide.actors.zombie.Zombie;
import zombicide.util.CardinalPoints;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;

public class Weapon extends Equipment{
	/**
	 * the number of dice to throw 
	 */
	protected int numberOfThrowDice;
	/**
	 * the threshold to pass to attack
	 */
	protected int threshold;
	/**
	 * the reach of the weapon
	 */
	protected int reach;
	/**
	 * the minimal reach of the weapon
	 */
	protected int minimalReach = 0;
	
	/**
	 * if the weapon is silent
	 */
	protected boolean silent;
	
	/**Creates a weapon
	 * @param ableToOpenADoor represents the ability of this weapon to open a door
	 * @param numberOfThrowDice the number of dice to throw
	 * @param threshold the threshold to pass
	 * @param damage the damage
	 * @param reach the reach of the weapon
	 * @param silent if the weapon is silent
	 */
	public Weapon(boolean ableToOpenADoor, int numberOfThrowDice, int threshold, int damage, int reach, boolean silent) {
		super(ableToOpenADoor,damage);
		this.weapon = true;
		this.numberOfThrowDice = numberOfThrowDice;
		this.threshold = threshold;
		this.damage = damage;
		this.reach = reach;
		this.silent = silent;
	}
	/**
	 * get the minimal reach
	 * @return the minimal reach
	 */
	public int getMinimalReach() {
		return this.minimalReach;
	}
	/**
	 * set the minimal reach
	 * @param mini the new minimal reach
	 */
	public void setMinimalReach(int mini) {
		this.minimalReach = mini;
	}
	/**
	 * get the number of dice to throw
	 * @return the number of dice to throw
	 */
	public int getNumberOfThrowDice() {
		return numberOfThrowDice;
	}
	/**
	 * set the number of dice to throw
	 * @param numberOfThrowDice the number of dice to throw
	 */
	public void setNumberOfThrowDice(int numberOfThrowDice) {
		this.numberOfThrowDice = numberOfThrowDice;
	}
	/**
	 * get the threshold
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}
	/**
	 * set the threshold
	 * @param threshold the threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * get the reach 
	 * @return the reach 
	 */
	public int getReach() {
		return reach;
	}
	/**
	 * set the reach 
	 * @param reach the reach 
	 */
	public void setReach(int reach) {
		this.reach = reach;
	}

	/**
	 * open a door
	 * @param d the door to open
	 */
	public void openDoor(Door d) {
		super.openDoor(d);
		this.player.makeNoise();
	}

	/**
	 * attack a zombie
	 */
	public boolean attack() {
		int tries = 0;
		boolean succeed = false;
		
		while(tries < this.nbOfThrow() && !succeed) {
			int throwD = this.player.throwDice();
			if(throwD >= this.threshold) {
				succeed = true;
				this.makeNoise();
			}
			System.out.println("You need to get "+this.threshold+" and you got "+throwD+" : "+(throwD >= this.threshold ? "succeeded" : "failed")+"\n");
			tries++;
		}
		return succeed;
}
	/**
	 * the number of trow possible
	 * @return the number of trow possible
	 */
	protected int nbOfThrow() {
		if(this.player.isLucky()) {
			return this.numberOfThrowDice + 1;
		}
		return this.numberOfThrowDice;
	}
	/**
	 * update the noise
	 */
	protected void makeNoise() {
		if(!this.silent) {
			this.player.makeNoise();
		}
	}
	
	@Override
	/**
	 * use the weapon
	 */
	public void use() {
		System.out.println("You can't use a weapon");
	}

	/**
	 * check if the zombies that are reachable	
	 * @param direction the direction to check
	 * @return the list of zombie that are reachable
	 */
	protected List<Zombie> checkAvaibleZombie(CardinalPoints direction){
		int i = 1;
		boolean inBounds = true;
		Zone currentZone = this.player.getZone();
		List<Zombie> l = new ArrayList<Zombie>();
		while(i<=this.reach && currentZone.isDoorOpen(direction)&& inBounds) {
			try {
				currentZone = currentZone.getNeighbour(direction);
				l.addAll(currentZone.getZombies());
			}catch(Exception e) {
				inBounds =false;
			}
			i++;
		}
		return l;
	}
	/**
	 * the list of reachable zombie
	 * @return the list of reachable zombie
	 */
	public List<Zombie> getReachableZombie(){
		List<Zombie> l = new ArrayList<Zombie>();
		if(this.minimalReach ==0) {
			l.addAll(this.player.getZombieOfZone());
		}
		if(this.minimalReach > 0) {
			l.addAll(this.checkAvaibleZombie(CardinalPoints.NORTH));
			l.addAll(this.checkAvaibleZombie(CardinalPoints.SOUTH));
			l.addAll(this.checkAvaibleZombie(CardinalPoints.EAST));
			l.addAll(this.checkAvaibleZombie(CardinalPoints.WEST));
		}
		return l;
	}

	/**
	 * give description of the weapon
	 * @return the description of the weapon
	 */
	public String toString() {
		return this.getClass().getSimpleName()+" [damageValue=" + this.damage + ", canOpenADoor=" + this.ableToOpenADoor +"]";
	}
	
}
