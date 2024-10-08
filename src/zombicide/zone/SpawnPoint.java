package zombicide.zone;

import zombicide.board.Board;

/** Class of SpawnPoint */
public class SpawnPoint extends Street{

	/** if the loot is enable */
	protected boolean lootEnable = false;

	/** the number of spawning zombies */
	protected int numberOfSpawningZombies;
	/**
	 * Create a SpawnPoint by given position and zombie numbers
	 * @param p the position
	 * @param numberOfSpawningZombies the number of spawning zombies
	 * @param b board
	 */
	public SpawnPoint(Position p, int numberOfSpawningZombies,Board b) {
		super(p,b);
		this.numberOfSpawningZombies = numberOfSpawningZombies;
		this.name = "W";
	}
}
