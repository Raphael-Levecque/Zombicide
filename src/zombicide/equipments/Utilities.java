package zombicide.equipments;

import java.util.ArrayList;
import java.util.List;

import zombicide.actors.zombie.Zombie;

public abstract class Utilities extends Equipment{
	/**
	 * builds a utilities with given parameters
	 * @param abbleToOpenADoor true if the equipment can open a door, false otherwise
	 */
	public Utilities(boolean abbleToOpenADoor) {
		super(abbleToOpenADoor,0);
		this.utility = true;
	}

	/**
	 * use the equipment
	 */
	public abstract void use();
	
	/**
	 * attack with the equipment
	 * @return false if the equipment is not a weapon, true otherwise
	 */
	public boolean attack() {
		return false;
	}
	/**
	 * get the reachable zombie
	 */
	public List<Zombie> getReachableZombie(){
		return new ArrayList<Zombie>();
	}
}
