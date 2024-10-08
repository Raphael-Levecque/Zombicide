package zombicide;

import zombicide.actions.*;
import zombicide.board.Board;
import zombicide.board.ClassicalBoard;
import zombicide.board.TrainingBoard;
import zombicide.exception.NoSuchZoneException;
import zombicide.zone.Zone;
import zombicide.actors.players.*;
import zombicide.actors.zombie.*;

import java.util.*;

/** Class of Game */
public class Game {

	/** the action manager */
	protected ActionManager actionManager;

	/** the board */
	protected Board board;
	
	/**
	 * Constructor of Game
	 * @param width the width of the board
	 * @param height the height of the board
	 * @param nbOfPlayers the number of players
	 */
	public Game(int width, int height, int nbOfPlayers) {
		this.actionManager = new ActionManager();
		this.initBoard(width,height);
		this.initPlayers(nbOfPlayers);
		this.addZombieToBoardCreation(height+width);
		this.initActions(actionManager, board);
	}
	
	/**
	 * Initialize the board
	 * @param width the width of the board
	 * @param height the height of the board
	 */
	protected void initBoard(int width, int height) {
		if(width == 5 && height == 5) {
			this.board = new TrainingBoard();
		}
		else {
			this.board = new ClassicalBoard(width,height);
		}
	}
	
	/**
	 * Check if the game is done
	 * @return true if the game is done, false otherwise
	 */
	public boolean isGameDone() {
		return board.getGlobalExperience() == 30 || board.getNumberOfZombie() == 0 || board.getNumberOfPlayer() == 0;
	}
	
	/**
	 * Add zombies to the board at the creation
	 * @param nb_zombie the number of zombies to add
	 */
	protected void addZombieToBoardCreation(int nb_zombie) {
		board.spawnZombieAtCreation(nb_zombie);
	}
	
	/**
	 * Initialize the actions
	 * @param am the action manager
	 * @param b the board
	 */
	protected void initActions(ActionManager am, Board b) {
		am.addAction(new OpenDoor(b));
		am.addAction(new HealAction());
		am.addAction(new LookAround());
		am.addAction(new MakeNoise());
		am.addAction(new Move());
		am.addAction(new Search());
		am.addAction(new TakeInHand());
		am.addAction(new UseEquipment());
		am.addAction(new Attack());
	}
	
	/**
	 * Get the board
	 * @return the board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Play the game
	 * @throws NoSuchZoneException if the zone does not exist
	 */
	public void play() throws NoSuchZoneException {
		this.displaySurvivors();
		System.out.println(this.board);
		while(!this.isGameDone()) {
			System.out.println("================");
			System.out.println("| Players turn |");
			System.out.println("================\n");
			while(board.hasNextPlayer() && !this.isGameDone()) {
				Player p = board.nextPlayer();
				this.playerPlays(p);
			}
			System.out.println("================");
			System.out.println("| Zombies turn |");
			System.out.println("================\n");
			for(Zombie z : board.getAllZombies()) {
				this.zombiePlays(z);
				
			}
			board.setNoiseAcrossBoardToDefault(); 
			this.updateAlivePlayers();
			this.board.spawnZombieLevel();
			System.out.println(this.board);
		}
		this.printReasonOfGameDone();
		System.out.println(this.board);
	}
	
	/**
	 * Display the survivors
	 */
	protected void displaySurvivors() {
		System.out.println("=============");
		System.out.println("| Survivors |");
		System.out.println("=============\n");
		for(Player p: this.board.getAllPlayers()) {
			System.out.println(p.getGameName());
		}
		System.out.println();
	}
	
	/**
	 * Update the alive players
	 */
	protected void updateAlivePlayers() {
		List<Player> alive = new ArrayList<>();
		for(Player p: this.board.getPlayers()) {
			if(this.board.getAllPlayers().contains(p)) {
				alive.add(p);
			}
		}
		this.board.setPlayers(alive);
	}
	
	/**
	 * Initialize the players
	 * @param nbPlayersToSpawn the number of players to spawn
	 */
	protected void initPlayers(int nbPlayersToSpawn) {
		this.board.initPlayers(nbPlayersToSpawn);
	}
	
	/**
	 * The player plays
	 * @param p the player
	 */
	protected void playerPlays(Player p) {
		System.out.println("==> "+p.getGameName());
		System.out.println(p+"\n");
		while(p.getCurrentActionPoints()>0 && !this.isGameDone()) {
			List<Action> doable = actionManager.getDoableActions(p);
			p.chooseAction(doable);
		}
		p.reinitActionPoints();
	}
	
	/**
	 * The zombie plays
	 * @param z the zombie
	 * @throws NoSuchZoneException if the zone does not exist
	 */
	protected void zombiePlays(Zombie z) throws NoSuchZoneException {
		while(z.getCurrentActionPoints()>0 && !this.isGameDone()) {
			Zone noisiest = board.getNoisiestZone();
			z.play(noisiest);
		}
		z.reinitActionPoints();
	}
   

	/**
	 * Print the reason of the game done
	 */
	public void printReasonOfGameDone() {
		if(board.getGlobalExperience() == 30) {
			System.out.println("=========================================");
			System.out.println("| Congratulations ! You reached level 30 |");
			System.out.println("=========================================\n");
		}
		if(board.getNumberOfZombie() == 0) {
			System.out.println("===========================================");
			System.out.println("| Congratulations ! You killed all zombies |");
			System.out.println("===========================================\n");
		}
		if(board.getNumberOfPlayer() == 0) {
			System.out.println("=====================================");
			System.out.println("| Game Over, all survivors are dead |");
			System.out.println("=====================================\n");
		}
	}
}


