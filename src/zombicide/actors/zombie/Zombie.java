package zombicide.actors.zombie;

import java.util.List;

import zombicide.actors.Actors;
import zombicide.actors.players.Player;
import zombicide.equipments.Equipment;
import zombicide.exception.NoSuchZoneException;
import zombicide.listchooser.RandomListChooser;
import zombicide.util.CardinalPoints;
import zombicide.zone.Zone;

/** Class of Zombie */
public class Zombie extends Actors{
	
	/** the damage the zombie does */
	protected int damage;

	/**
	 * Builds a zombie with given parameters
	 * @param lifePoints the life points of the zombie
	 * @param damage the damage the zombie does
	 * @param actionPoints the action points the zombie has
	 */
	public Zombie(int lifePoints,int damage,int actionPoints) {
		super();
		this.damage=damage;
		this.actionPoints=actionPoints;
		this.lifePoints=lifePoints;
	}
	/**
	 * attack a random player in the zone
	 */
	public void attack() {
		/*Random random = new Random();
		List<Player> players = zone.getPlayers();
		if (players.size()>0) {
			Player p = players.get(random.nextInt(players.size()));
			p.takeDamage(this.damage);
		}*/
		if(this.zone.doesAllowAttack()){List<Player> listPlayer = this.getPlayersOfZone();
			Player player = this.choosePlayer(listPlayer);
			System.out.println("  "+this.description()+" is attacking "+player.getGameName()+"\n");
			boolean killed = player.takeDamage(this.damage);
			if(killed) {
				System.out.println(player.getGameName()+" is dead\n");
			}
		}
	}
	/**
	 * make a choice of a player
	 * @param player the list of players
	 * @return the chosen player
	 */
	public Player choosePlayer (List<Player> players) {
		RandomListChooser<Player> randChoice= new RandomListChooser<>();
		Player chosenElement = randChoice.choose(this.description()+" is choosing a player to attack", players);
		return chosenElement;
	}

	/**
	 * get the players of the zone
	 * @return the players of the zone
	 */
	public List<Player> getPlayersOfZone(){
		return this.zone.getPlayers();
	}
	/**
	 * decrease the life points of damage
	 * and analyze if the zombie is still alive, if not removes it
	 * @param damage the damage the zombie took
	 * @return true if the zombie is dead, false otherwise
	 */
	public boolean takeDamage(int damage){
		this.lifePoints=this.lifePoints-damage;
		if(this.getLifePoints()<1) {
			die();
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * take damage from a weapon
	 * @param e the equipment
	 * @return true if the zombie is dead, false otherwise
	 */
	public boolean takeDamageFrom(Equipment e) {
		if(e.isWeapon()) {
			return this.takeDamage(e.getDamage());
		}
		return false;
		
	}
	/**
	 * get the damage the zombie does
	 * @return the damage the zombie does
	 */
	public int getDamage() {
		return damage;
	}
	/**
	 * set the damage
	 * @param damage 
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	/**
	 * kill the zombie
	 */
	protected void die() {
		zone.removeZombie(this);
	}


	/**
	 * move the zombie to the noisiest zone if a door is closed the zombie will not move
	 * @param noisiestZone the noisiest zone
	 * @throws NoSuchZoneException 
	 */
	public void move(Zone noisiestZone) throws NoSuchZoneException{
		if (noisiestZone!=null) {
			CardinalPoints direct = this.zone.inWhichDirectionIs(noisiestZone);
			if (direct != null) {
				System.out.println(this.description()+" has moved to "+direct+"\n");
				this.zone.moveZombieToNeighbour(direct,this);
			}
		}
	}
	
	/**
	 * play the zombie
	 * @param noisiestZone the noisiest zone
	 * @throws NoSuchZoneException 
	 */
	public void play(Zone noisiestZone) throws NoSuchZoneException {
		if(!this.zone.getPlayers().isEmpty()) {
			this.attack();
		}
		else {
			this.move(noisiestZone);
		}
		this.decreaseActionPoints(1);
	}
	
	/**
	 * return true if the zombie is dead, false otherwise
	 * @return true if the zombie is dead, false otherwise
	 */
	public boolean isDead() {
		if (this.lifePoints<=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * give a short description of the zombie
	 * @return the description of the zombie
	 */
	public String description() {
		return this.getClass().getSimpleName()+" in zone "+zone.getPosition();
	}

	/**
	 * give description of the zombie
	 * @return the description of the zombie
	 */
	public String toString() {
		return this.getClass().getSimpleName()+" in zone "+zone.toString()+" with "+lifePoints+" life points";
	}
}
