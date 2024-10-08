package zombicide.equipments;

import java.util.List;

import zombicide.actors.players.Player;
import zombicide.actors.zombie.Zombie;
import zombicide.zone.doors.Door;

/** class equipment*/
public abstract class Equipment {
	
	/*Attributes*/
	
	/*States if this equipment can open a door*/
	protected boolean ableToOpenADoor;

	/*The player who has this equipment*/
	protected Player player;
	
	/*States if this equipment is a weapon*/
	protected boolean weapon; 
	
	/**
	 * the damage
	 */
	protected int damage;
	
	/*States if this equipment is a utility*/
	protected boolean utility;
	
	/** Creates an equipment
	 * @param ableToOpenADoor represents the ability of this equipment to open a door
	 * @param damage the damage of the equipment
	 */
	public Equipment(boolean ableToOpenADoor,int damage) {
		this.ableToOpenADoor = ableToOpenADoor;
		this.damage = damage;
		this.player = null;
		this.weapon = false;
		this.utility = false;
	}
	/*Methods*/
	/**
	 * true if the equipment can open a door, false otherwise
	 * @return true if the equipment can open a door, false otherwise
	 */
	public boolean isAbleToOpenADoor() {
		return ableToOpenADoor;
	}


	/**
	 * set the player to p
	 * @param p the player to be set
	 */
	public void setPlayer(Player p){
		this.player=p;
	}

	/**
	 * get the player
	 * @return the player
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * get the damage
	 * @return the damage
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * set the damage
	 * @param damage the damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * unset the player
	 */
	public void unsetPlayer(){
		this.player=null;
	}
	
	/** 
	 * @return true if the equipment is a weapon, false otherwise
	 */
	public boolean isWeapon() {
		return this.weapon;
	}
	
	/** 
	 * @return true if the equipment is a utility, false otherwise
	 */
	public boolean isUtility() {
		return this.utility;
	}

	/**
	 * use the equipment
	 * @return the result of using the equipment
	 */
	public abstract void use();
	
	/**
	 * attack with the equipment
	 * @return the result of the attack
	 */
	public abstract boolean attack();
	
	/**
	 * get the reachable zombies
	 * @return the reachable zombies
	 */
	public abstract List<Zombie> getReachableZombie();
	
	/**
	 *@param d the door to open
	 */
	public void openDoor(Door d) {
		if(this.isAbleToOpenADoor()) {
			d.open();
		}
	}

	/**
	 * get description of the equipment
	 * @return the description of the equipment
	 */
	public String toString(){
		return this.getClass().getSimpleName();
	}

}
