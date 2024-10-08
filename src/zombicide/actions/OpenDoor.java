package zombicide.actions;

import java.util.List;
import java.util.Random;

import zombicide.actors.players.Player;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;

/** Class of OpenDoor */
public class OpenDoor extends Action {
	
	/** List of spawn zones */
	protected Board board;
	
	/** List of spawnable zombies */
	protected List<Zombie> spawnableZombies;
	
	/**
	 * Constructor of the action open door
	 * @param spawnZones the list of spawn zones
	 */
		public OpenDoor(Board b) {
			super(1);
			this.board = b;
		}
	
	/**
	 * Check if the action is doable
	 * @param p the player
	 * @return true if the action is doable, false otherwise
	 */
	public boolean doable(Player p) {
		return p.canOpenDoor();
	}
	
	/**
	 * Open the door
	 * @param p the player
	 */
	public void use(Player p) {
		p.openDoor();
		this.decreaseActionPoints(p);
		this.spawnZombies();
	}
	
	/**
	 * Spawn zombies
	 */
	protected void spawnZombies() {
		Random rand = new Random();
		int nbOfZombiesToSpawn = rand.nextInt(3) + 1;
		this.board.spawnZombie(nbOfZombiesToSpawn);
	}
	
	
}
