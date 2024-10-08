package zombicide.board;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import zombicide.actors.players.Fighter;
import zombicide.actors.players.Healer;
import zombicide.actors.players.Lucky;
import zombicide.actors.players.Player;
import zombicide.actors.players.Snoop;
import zombicide.actors.zombie.Zombie;
import zombicide.util.CardinalPoints;
import zombicide.util.Noise;
import zombicide.zone.Building;
import zombicide.zone.Continentale;
import zombicide.zone.Pharmacy;
import zombicide.zone.Position;
import zombicide.zone.SpawnPoint;
import zombicide.zone.Street;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;
import zombicide.actors.zombie.*;


/** Class of Board */
public abstract class Board {
	/**
	 * the zones of the board
	 */
	protected Zone[][] theZones;
	/**
	 * the width of the board
	 */
	protected int width;
	/**
	 * the height of the board
	 */
	protected int height;
	/*Offset used to specify the range [border+2,border-2]*/
	protected static final int OFFSET = 2;
	/*Random instance to generate a random number*/
	protected static final Random RANDOM_POS = new Random();
	/*Position of the main cross road*/
	protected Position mainCrossRoadPos;
	/*Player on the board*/
	protected List<Player> player;
	/*the sound in the zones*/
	protected Map<Zone,Noise> theSounds;
	/*counter for next player*/
	protected int cpt = 0;
	
	
	/** Generates a new Board
	 * @param width the width of the board
	 * @param height the height of the board
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.theZones = new Zone[width][height];
		this.player = new ArrayList<>();
		this.initBoard();
	}
	
	/**
	 * @return the width of the board
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @return the number of player on the board
	 */
	public int getNumberOfPlayer() {
		return this.player.size();
	}
	/**
	 * @return the height of the board
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @param x the horizontal location of the zone
	 * @param y the vertical location of the zone
	 * @return the zone at coordinates (x,y)   
	 */
	public Zone getZone(int x, int y) {
		return theZones[x][y];
	}
	
	/**
	 * @return the mainCrossRoad of the board
	 */
	public Zone getMainCrossRoad() {
		return this.theZones[this.mainCrossRoadPos.getX()][this.mainCrossRoadPos.getY()];
	}

	/**
	 * set the players on the board
	 * @param players the list of players to set
	 */
	public void setPlayers(List<Player> players) {
		this.player = players;
	}

	/**
	 * @return the list of players on the board
	 */
	public List<Player> getPlayers() {
		return this.player;
	}
	
	/**
	 * spawn zombie when door is open
	 * @param nbOfZombiesToSpawn to spawn
	 */
	public void spawnZombie(int nbOfZombiesToSpawn) {
		List<Zombie> spawnableZombies = this.generateSpawnableZombies(nbOfZombiesToSpawn);
		for(int i = 0 ; i < nbOfZombiesToSpawn ; i++) {
			Zone randZone = this.randomZone(this.getSpawnPoints());
			Zombie z = this.randomZombie(spawnableZombies);
			randZone.addZombie(z);
			spawnableZombies.remove(z);
		}
	}
	/**
	 * spawn zombie at the creation of the game
	 * @param nbOfZombiesToSpawn to spawn
	 */
	public void spawnZombieAtCreation(int nbOfZombiesToSpawn) {
		List<Zombie> spawnableZombies = this.generateSpawnableZombies(nbOfZombiesToSpawn);
		for(int i = 0 ; i < nbOfZombiesToSpawn ; i++) {
			Zone randZone = this.randomZone(this.getStreet());
			Zombie z = this.randomZombie(spawnableZombies);
			randZone.addZombie(z);
			spawnableZombies.remove(z);
		}
	}
	/**
	 * get the Streets
	 * @return the list of Street
	 */
	protected List<Zone> getStreet(){
		List<Zone> zones = new ArrayList<Zone>();
		for(int i = 0;i<this.width;i++) {
			for(int j = 0;j<this.height;j++) {
				if(!this.theZones[i][j].isSearchable()) {
					zones.add(this.theZones[i][j]);
				}
			}
		}
		return zones;
	}
	/**
	 * get a random zone in a list of zones
	 * @param zones the list of zones to chose from
	 * @return the zone chosen at random
	 */
	protected Zone randomZone(List<Zone> zones) {
		Random rand = new Random();
		return zones.get(rand.nextInt(zones.size()));
	}
	/**
	 * generate the list of spawnable zombie
	 * @param nbOfZombiesToSpawn
	 * @return the list of spawnable zombies
	 */
	protected List<Zombie> generateSpawnableZombies(int nbOfZombiesToSpawn) {
		List<Zombie> spawnableZombies = new ArrayList<>();
		spawnableZombies.add(new Abomination());
		spawnableZombies.add(new Huge());
		for(int i = 0; i < nbOfZombiesToSpawn ; i++) {
			spawnableZombies.add(new Runner());
			spawnableZombies.add(new Walker());
		}
		return spawnableZombies;
	}
	/**
	 * get a random Zombie in list
	 * @param zombies the list to chose from
	 * @return the zombie chosen
	 */
	protected Zombie randomZombie(List<Zombie> zombies) {
		Random rand = new Random();
		return zombies.get(rand.nextInt(zombies.size()));
	}
	
	/**
	 * create a temporary zone
	 * @return the temporary zone
	 */
	protected Zone temporaryZone() {
		return new Zone(new Position(-1,-1),this);
	}
	
	/**
	 * spawn zombie in a zone
	 * @param zombie the zombie to spawn
	 * @param zone the zone to spawn the zombie in
	 */
	public void spawnZombie(Zombie zombie, Zone zone) {
		zone.addZombie(zombie);
	}
	
	/**
	 * get the spawn points
	 * @return the list of spawn points
	 */
	protected List<Zone> getSpawnPoints() {
		List<Zone> spawn = new ArrayList<>();
		spawn.add(this.theZones[this.mainCrossRoadPos.getX()][this.height-1]);
		spawn.add(this.theZones[this.mainCrossRoadPos.getX()][0]);
		spawn.add(this.theZones[0][this.mainCrossRoadPos.getY()]);
		spawn.add(this.theZones[this.width-1][this.mainCrossRoadPos.getY()]);
		
		return spawn;
	}
	
	
	/**
	 * Initialize the board, randomly divides it into buildings and streets
	 * randomly placing a Continentale building and a Pharmacy building
	 */
	protected void initBoard() {
		this.divideBoard(0, this.width-1, 0, this.height-1);
		this.initSewers();
		this.initContinentale();
		this.initPharmacy();
		this.fillBoardWithBuildings();
		this.initDoors();
		this.openStreets();
		this.initNeighbours();
	}
	
	
	/** Divides the board with a main cross road
	 * @param startX the horizontal starting position of the block to divide
	 * @param endX the horizontal ending position of the block to divide
	 * @param startY the vertical starting position of the block to divide
	 * @param endY the vertical ending position of the block to divide
	 */
	protected void divideBoard(int startX, int endX, int startY, int endY) {
		int currentWidth = endX - startX + 1;
		int currentHeight = endY - startY + 1;
		
		if (currentWidth >= 5 && currentHeight >= 5) {
			int newStartY = startY + OFFSET;
			int newEndY = endY - OFFSET;
			int newStartX = startX + OFFSET;
			int newEndX = endX - OFFSET;
			
			/*Random position for the vertical street of the main cross road*/
			int randomXPos = ClassicalBoard.RANDOM_POS.nextInt(newEndX - newStartX + 1) + newStartX;
			/*Random position for the horizontal street of the main cross road*/
			int randomYPos = ClassicalBoard.RANDOM_POS.nextInt(newEndY - newStartY + 1) + newStartY;
			
			this.initMainCrossRoad(startX, endX, startY, endY, randomXPos, randomYPos);
				
			/*Divide top left corner*/
			this.subDivide(startX,randomXPos-1,startY,randomYPos-1);
			/*Divide top right part*/
			this.subDivide(randomXPos+1, endX,startY, randomYPos-1);
			/*Divide left part*/
			this.subDivide(startX, randomXPos-1, randomYPos+1, endY);
			/*Divide right part*/
			this.subDivide(randomXPos+1, endX, randomYPos+1, endY);
		}
		else {
			this.subDivide(startX, endX, startY, endY);
		}
	}
	
	/** Initialize the main cross road
	 * @param startX the horizontal starting position of the block to divide
	 * @param endX the horizontal ending position of the block to divide
	 * @param startY the vertical starting position of the block to divide
	 * @param endY the vertical ending position of the block to divide
	 * @param randomXPos random position for the vertical street of the main cross road
	 * @param randomYPos random position for the horizontal street of the main cross road
	 */
	protected void initMainCrossRoad(int startX, int endX, int startY, int endY, int randomXPos, int randomYPos) {
		mainCrossRoadPos = new Position(randomXPos,randomYPos);
		this.lineToStreet(startX, endX, randomYPos);
		this.columnToStreet(startY, endY, randomXPos);
	}
	
	/**
	 * Initialize four sewers at extremity of the main cross road
	 */
	protected void initSewers() {
		int xPos = this.mainCrossRoadPos.getX();
		int yPos = this.mainCrossRoadPos.getY();

		/*Top sewer */
		this.theZones[xPos][0] = new SpawnPoint(new Position(xPos,0),0,this);;
		
		/*Bottom sewer */
		this.theZones[xPos][this.height-1] = new SpawnPoint(new Position(xPos,this.height-1),0,this);;
		
		/*Left sewer */
		this.theZones[0][yPos] = new SpawnPoint(new Position(0,yPos),0,this);;
		
		/*Right sewer */
		this.theZones[this.width-1][yPos] = new SpawnPoint(new Position(this.width-1, yPos),0,this);;
	}

	/** Changes a column to a street 
	 * @param startY the start index of the column 
	 * @param endY the end index of the column  
	 * @param columnIndex the horizontal position of the column
	 */
	protected void columnToStreet(int startY, int endY, int columnIndex) {
		for(int y = startY; y <= endY; y++) {
			this.theZones[columnIndex][y] = new Street(new Position(columnIndex,y),this);;
		}
	}
	
	
	
	/** Changes a line to a street 
	 * @param startX the start index of the line
	 * @param endX the end index of the line
	 * @param lineIndex the vertical position of the line
	 */
	protected void lineToStreet(int startX, int endX, int lineIndex) {
		for(int x = startX; x <= endX ; x++) {
			this.theZones[x][lineIndex] = new Street(new Position(x,lineIndex),this);;
		}
	}
	
	
	/** Divide the zone with a street
	 * @param startX the horizontal starting position of the block to divide
	 * @param endX the horizontal ending position of the block to divide
	 * @param startY the vertical starting position of the block to divide
	 * @param endY the vertical ending position of the block to divide
	 */
	protected void subDivide(int startX, int endX, int startY, int endY) {
		int currentWidth = endX - startX + 1;
		int currentHeight = endY - startY + 1;

			
		if (currentHeight >= 5) {
			int newStartY = startY + OFFSET;
			int newEndY = endY - OFFSET;
				
			int randomYPos = ClassicalBoard.RANDOM_POS.nextInt(newEndY - newStartY + 1) + newStartY;
				
			this.lineToStreet(startX, endX, randomYPos);
				
			/*Divide top part*/
			this.subDivide(startX,endX,startY,randomYPos-1);
			/*Divide bottom part*/
			this.subDivide(startX, endX,randomYPos+1, endY);
		}
		
		else if (currentWidth >= 5) {
				int newStartX = startX + OFFSET;
				int newEndX = endX - OFFSET;
				
				int randomXPos = ClassicalBoard.RANDOM_POS.nextInt(newEndX - newStartX + 1) + newStartX;
				
				this.columnToStreet(startY, endY, randomXPos);
				
				/*Divide left part*/
				this.subDivide(startX, randomXPos-1, startY, endY);
				/*Divide right part*/
				this.subDivide(randomXPos+1, endX, startY, endY);

		}
	}	
	
	/**
	 * Fill the board with building
	 */
	protected void fillBoardWithBuildings() {
		for (int y = 0 ; y < this.height ; y++) {
			for (int x = 0 ; x < this.width ; x++) {
				this.initBuilding(x, y);
			}
		}
	}
	
	/** Initialize the building with its doors
	 * @param xPos the horizontal position of the building
	 * @param yPos the vertical position of the building
	 */
	protected void initBuilding(int xPos, int yPos) {
		if (this.theZones[xPos][yPos] == null) {
			this.theZones[xPos][yPos] = new Building(new Position(xPos,yPos),this);
		}
	}
	
	/** Initialize doors 
	 */
	protected void initDoors() {
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				this.initNorthDoor(x, y);
				this.initSouthDoor(x, y);
				this.initWestDoor(x, y);
				this.initEastDoor(x, y);
			}
		}
	}

	/** Initialize north door of the building at coordinates (x,y)
	 * @param x the horizontal position of the building of this door
	 * @param y the vertical position of the building of this door
	 */
	protected void initNorthDoor(int x,int y) {
		if (y == 0) {
			this.theZones[x][y].addDoor(CardinalPoints.NORTH,new Door(false));
		}
		else {
			Zone aboveZone = this.theZones[x][y-1];
			Door door = aboveZone.getDoor(CardinalPoints.SOUTH);
			this.theZones[x][y].addDoor(CardinalPoints.NORTH,door);
		}
	}
	
	/** Initialize south door of the building at coordinates (x,y)
	 * @param x the horizontal position of the building of this door
	 * @param y the vertical position of the building of this door
	 */
	protected void initSouthDoor(int x,int y) {
		if (y == this.height-1) {
			this.theZones[x][y].addDoor(CardinalPoints.SOUTH,new Door(false));
		}
		else {
			this.theZones[x][y].addDoor(CardinalPoints.SOUTH,new Door(true));
		}
	}
	
	/** Initialize west door of the building at coordinates (x,y)
	 * @param x the horizontal position of the building of this door
	 * @param y the vertical position of the building of this door
	 */
	protected void initWestDoor(int x, int y) {
		if (x == 0) {
			this.theZones[x][y].addDoor(CardinalPoints.WEST,new Door(false));
		}
		else {
			Zone leftZone = this.theZones[x-1][y];
			Door door = leftZone.getDoor(CardinalPoints.EAST);
			this.theZones[x][y].addDoor(CardinalPoints.WEST,door);
		}
	}
	
	/** Initialize East door of the building at coordinates (x,y)
	 * @param x the horizontal position of the building of this door
	 * @param y the vertical position of the building of this door
	 */
	protected void initEastDoor(int x, int y) {
		if (x == this.width-1) {
			this.theZones[x][y].addDoor(CardinalPoints.EAST,new Door(false));
		}
		else {
			this.theZones[x][y].addDoor(CardinalPoints.EAST,new Door(true));
		}
	}
	
	
	/**
	 * open the doors of the streets
	 */
	protected void openStreets() {
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				if (this.theZones[x][y] instanceof Street) {
					/*Open south door*/
					if (y < this.height-1 && this.theZones[x][y+1] instanceof Street) {
						this.theZones[x][y].openDoor(CardinalPoints.SOUTH);
					}

					/*Open east door*/
					if (x < this.width-1 && this.theZones[x+1][y] instanceof Street) {
						this.theZones[x][y].openDoor(CardinalPoints.EAST);
					}
				}
			}
		}
	}
	/**
	 * init the neighbours fot the map
	 */
	protected void initNeighbours() {
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				/*North neighbour*/
				if (y != 0) {
					Zone aboveZone = this.theZones[x][y-1];
					this.theZones[x][y].addNeighbour(aboveZone, CardinalPoints.NORTH);
				}

				/*South neighbour*/
				if (y != this.height-1) {
					Zone southZone = this.theZones[x][y+1];
					this.theZones[x][y].addNeighbour(southZone, CardinalPoints.SOUTH);
				}

				/*Left neighbour*/
				if (x != 0) {
					Zone leftZone = this.theZones[x-1][y];
					this.theZones[x][y].addNeighbour(leftZone, CardinalPoints.WEST);
				}
				
				/*Right neighbour*/
				if (x != this.width-1) {
					Zone rightZone = this.theZones[x+1][y];
					this.theZones[x][y].addNeighbour(rightZone, CardinalPoints.EAST);
				}
			}
		}
	}

	/**
	 * Initialize the Continental
	 */
	protected void initContinentale() {
		boolean initiated = false;
		
		while (!initiated) {
			int randomXPos = ClassicalBoard.RANDOM_POS.nextInt(this.width - 1) + 1;
			int randomYPos = ClassicalBoard.RANDOM_POS.nextInt(this.height - 1) + 1;
			
			if (this.theZones[randomXPos][randomYPos] == null) {
				this.theZones[randomXPos][randomYPos] = new Continentale(new Position(randomXPos,randomYPos),this);
				initiated = true;
			}
		}
		
	}
	
	/**
	 * Initialize the Pharmacy
	 */
	protected void initPharmacy() {
		boolean initiated = false;
		
		while (!initiated) {
			int randomXPos = ClassicalBoard.RANDOM_POS.nextInt(this.width - 1) + 1;
			int randomYPos = ClassicalBoard.RANDOM_POS.nextInt(this.height - 1) + 1;
			
			if (this.theZones[randomXPos][randomYPos] == null) {
				this.theZones[randomXPos][randomYPos] = new Pharmacy(new Position(randomXPos,randomYPos),this);
				initiated = true;
			}
		}
		
	}
	

	/**
	 * create the string to represent the board
	 */
	public String toString() {
		String ret = "";
		for(int y =0; y<this.height; y++){
			int cmpt =0;
			for(int o = 0; o<3; o++) {
				for(int x =0; x<this.width; x++){
					if(o==0) {
						if (this.getZone(x, y).isDoorOpen(CardinalPoints.NORTH)) {
							ret += "    ";
						}
						else {
							ret += "----";
						}
					}
					else {
						if (this.getZone(x, y).isDoorOpen(CardinalPoints.WEST)) {
							ret += " ";
						}
						else {
							ret += "|";
						}
						ret += theZones[x][y].toString().substring(cmpt, cmpt+3);
					}
				}
			if (o!=0) {
				cmpt += 3;
			}
			ret += "|";
			ret += "\n";
			}
		}
		for(int q = 0; q<this.width; q++) {
			ret+= "----";
		}
		return ret;
	}

	/**
	 * @return the noisiest zone
	 */
	public Zone getNoisiestZone() {
		Zone noisiestZone = null;
		int maxNoise = 0;
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				Zone currentZone = this.theZones[x][y];
				int noiseLevel = currentZone.getNoiseLevel();
				if (noiseLevel > maxNoise) {
					maxNoise = noiseLevel;
					noisiestZone = currentZone;
				}
			}
		}
		return noisiestZone;
	}

	/**
	 * @param direction the direction of the door
	 * @param x the horizontal position of the zone
	 * @param y the vertical position of the zone
	 * @return if the door is open
	 */
	public boolean isDoorOpen(CardinalPoints direction, int x, int y) {
		return this.theZones[x][y].isDoorOpen(direction);
	}

	/**
	 * @param minimalReach the minimal reach
	 * @param maximalReach the maximal reach
	 * @param derection the direction of the door
	 * @param x the horizontal position of the zone
	 * @param y the vertical position of the zone
	 * @return the list of available zombies
	 */
	public List<Zombie> checkAvaibleZombies(int minimalReach, int maximalReach, CardinalPoints direction, int x, int y){
		int i = minimalReach;
		List<Zombie> l = new ArrayList<Zombie>();
		if (direction == CardinalPoints.NORTH) {
			while(i<=maximalReach && y+i<= this.getHeight() && this.isDoorOpen(CardinalPoints.SOUTH, x, y+1)){
				l.addAll(this.getZone(x, y+i).getZombies());
				i++;
			}
		}
		else if (direction == CardinalPoints.SOUTH) {
			while(i<=maximalReach && y-i>=0 && this.isDoorOpen(CardinalPoints.NORTH, x, y-i)){
				l.addAll(this.getZone(x, y-i).getZombies());
				i++;
			}
		}
		else if (direction == CardinalPoints.EAST) {
			while(i<=maximalReach && x+i<= this.getWidth() && this.isDoorOpen(CardinalPoints.EAST, x+i, y)){
				l.addAll(this.getZone(x+i, y).getZombies());
				i++;
			}
		}
		else if (direction == CardinalPoints.WEST) {
			while(i<=maximalReach && x-i>=0 && this.isDoorOpen(CardinalPoints.WEST, x-i, y)){
				l.addAll(this.getZone(x-i, y).getZombies());
				i++;
			}
		}
	return l;
	}

	/**
	 * get all zombies on the board
	 * @return the list of zombies
	 */
	public List<Zombie> getAllZombies() {
		List<Zombie> zombies = new ArrayList<>();
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				zombies.addAll(this.theZones[x][y].getZombies());
			}
		}
		return zombies;
	}	
	
	/**
	 * get all player on the board
	 * @return the list of players
	 */
	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<>();
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				players.addAll(this.theZones[x][y].getPlayers());
			}
		}
		return players;
	}

	/**
	 * Turn the noise around all the board to 0 usefull when changing rounds
	 */
	public void setNoiseAcrossBoardToDefault() {
		for (int y = 0; y<this.height ; y++) {
			for (int x = 0; x<this.width; x++) {
				this.theZones[x][y].turnToDefaultNoiseInzoneZone();
			}
		}
	}

	/**
	 * getNumberOfZombie
	 * @return int the number of zombie in the board
	 */
	public int getNumberOfZombie() {
		return this.getAllZombies().size();
	}

	
	/**
	 * spawn zombie in function of average level of players 
	 */
	public void spawnZombieLevel() {
		double nbOfZombiesToSpawnAverage = 0;
		for (Player p : this.getPlayers()) {
			nbOfZombiesToSpawnAverage += p.getExpertisePoints();
		}
		int nbOfZombiesToSpawn = (int) Math.ceil(nbOfZombiesToSpawnAverage/this.getNumberOfPlayer());
		this.spawnZombie(nbOfZombiesToSpawn);
	}
	
	/**
     * return the next player in the list to play
     * @return the next player in the list to play
     */
    public Player nextPlayer(){
    	Player p = this.player.get(cpt);
    	this.cpt = this.cpt+1;
    	return p;
    }
    /**
     * get the global experience of the players
     * @return the global experience of the players of the board
     */
    public int getGlobalExperience() {
    	int globalXP = 0;
    	for(Player p : this.player) {
    		globalXP += p.getExpertisePoints();
    	}
    	return globalXP;
    }
    
	/**
	 * Initialize the players on the board
	 * @param nbPlayersToSpawn the number of players to spawn
	 */
	public void initPlayers(int nbPlayersToSpawn) {
		List<Player> spawnablePlayers = this.generateSpawnablePlayers(nbPlayersToSpawn);
		for(int i = 0 ; i < nbPlayersToSpawn ; i++) {
			Player p = this.randomPlayer(spawnablePlayers);
			this.player.add(p);
			p.setGameName("Survivor "+(i+1));
			this.getMainCrossRoad().addPlayer(p);
			spawnablePlayers.remove(p);
		}
	}
    
	/**
	 * generate the list of spawnable players
	 * @param nbPlayersToSpawn the number of players to spawn
	 * @return the list of spawnable players
	 */
	protected List<Player> generateSpawnablePlayers(int nbPlayersToSpawn) {
		List<Player> spawnablePlayers = new ArrayList<>();
		for(int i = 0; i < nbPlayersToSpawn; i++) {
			spawnablePlayers.add(new Snoop());
			spawnablePlayers.add(new Lucky());
			spawnablePlayers.add(new Healer());
			spawnablePlayers.add(new Fighter());
		}
			
		return spawnablePlayers;
	}
	
	/**
	 * remove a player from the board
	 * @param p
	 */
	public void removePlayer(Player p) {
		this.player.remove(p);
	}
	
	/**
	 * get a random player in list
	 * @param players the list to chose from
	 * @return the zombie chosen
	 */
	protected Player randomPlayer(List<Player> players) {
		Random rand = new Random();
		return players.get(rand.nextInt(players.size()));
	}

	/**
	 * return if there is a next player
	 * @return true if there is a next player, false otherwise
	 */
	public boolean hasNextPlayer() {
		if(this.cpt == this.getNumberOfPlayer()) {
			this.cpt = 0;
			return false;
		}
		return true;
	}

}


