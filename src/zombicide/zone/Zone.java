package zombicide.zone;

import zombicide.actors.players.Player;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.equipments.Equipment;
import zombicide.exception.NoSuchZoneException;
import zombicide.util.CardinalPoints;
import zombicide.util.Noise;
import zombicide.zone.doors.Door;

import java.util.*;

/** Class of Zone */
public class Zone {
	/**
	 * the position of the zone
	 */
	protected Position position;

	/**
	 * the name of the zone
	 */
	protected String name;
	
	/**
	 * the doors of the zone
	 */
	protected Map<CardinalPoints,Door> doors;
	/**
	 * the neighbours of the zone
	 */
	protected Map<CardinalPoints,Zone> neighbours;

	/**
	 * the players on the zone
	 */
	protected List<Player> players;
	
	/**
	 * the zombies on the zone
	 */
	protected List<Zombie> zombies;
	
	/**
	 * the noise of the zone
	 */
	protected Noise noise;
	
	/**
	 * the equipment that are in the zone
	 */
	protected List<Equipment> equipments;
	
	/** the board*/
	protected Board board;
	/** if the zone is searchable*/
	
	protected boolean searchable;
	
	/**if players can atteck in the zone*/
	
	protected boolean allowAttack;
	
	/**
	 * construct a zone with its position
	 * @param p the position
	 * @param b the baord
	 */
	public Zone(Position p,Board b ){
		this.position=p;
		this.doors= new HashMap<CardinalPoints,Door>();
		this.noise=new Noise();
		this.players=new ArrayList<Player>();
		this.zombies=new ArrayList<Zombie>();
		this.neighbours=new HashMap<CardinalPoints,Zone>();
		this.name = "Z";
		this.equipments = new ArrayList<Equipment>();
		this.board = b;
		this.allowAttack = true;
		this.searchable = true;
	}
	
	/**
	 * get the position of the zone
	 * @return the position 
	 */
	public Position getPosition() {
		return position;
	}
	

	/**
	 * @return the doors
	 */
	public Map<CardinalPoints, Door> getDoors() {
		return doors;
	}
	/**
	 * get the door of Cardinal point
	 * @param cardPoint
	 * @return the door of the cardinal point
	 */
	public Door getDoor(CardinalPoints cardPoint)  {
		return this.doors.get(cardPoint);
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the zombies
	 */
	public List<Zombie> getZombies() {
		return zombies;
	}

	/**
	 * @return the noise
	 */
	public int getNoiseLevel() {
		return noise.getNoiseLevel();
	}

	
	/**
	 * @param p the  player to add 
	 */
	public void addPlayer(Player p) {
		this.players.add(p);
		p.setZone(this);
	}
	
	/**
	 * @param p the player to remove
	 * @return true if the player was removed, false otherwise
	 */
	public boolean removePlayer(Player p) {
		return this.players.remove(p);
	}
	
	/**
	 * @param z the zombie to add
	 */
	public void addZombie(Zombie z) {
		this.zombies.add(z);
		z.setZone(this);
	}
	
	/**
	 * @param z the zombie to remove
	 * @return true if the zombie was removed, false otherwise
	 */
	public boolean removeZombie(Zombie z) {
		return this.zombies.remove(z);
	}
	
	/**
	 * increase the noise level of the zone
	 */
	public void increaseNoise() {
		this.noise.setNoiseLevel(this.noise.getNoiseLevel()+1);
	}
	
	/**
	 * set the noise level at 0
	 */
	public void removeNoise() {
		this.noise.setNoiseLevel(0);
	}
	
	
	/**
	 * @param CardinalPoints the cardinal point
	 * @return true if the door is open, false otherwise
	 */
	public boolean isDoorOpen(CardinalPoints cardPoint) {
		return this.getDoor(cardPoint).isOpen();
	}
	
	/**
	 * Adds a door at a given direction
	 * @param door the door to add
	 * @param cardPoint the cardinal point
	 */
	public void addDoor(CardinalPoints cardPoint,Door door) {
		this.doors.put(cardPoint,door);
	}

	/**
	 * Opens a door at a given direction 
	 * @param cardPoint the cardinal point
	 */
	public void openDoor(CardinalPoints cardPoint) {
		this.getDoor(cardPoint).open();
	}
	
	/**
	 * add a door at a given direction to the zone 
	 * @param zone the zone to add
	 * @param cardPoint the cardinal point
	 */
	public void addNeighbour(Zone zone,CardinalPoints cardPoint) {
		this.neighbours.put(cardPoint,zone);
	}
	
	/**
	 * get the zone that the neighbour
	 * @param cardPoint 
	 * @return the neighbour zone
	 * @throws NoSuchZoneException
	 */
	public Zone getNeighbour(CardinalPoints cardPoint) throws NoSuchZoneException{
			Zone neighbour = this.neighbours.get(cardPoint);
			if(neighbour != null) {
				return neighbour;
			}
			throw new NoSuchZoneException("No zone at the "+cardPoint+" of this zone");

	}
	/**
	 * add the player to neighbour zone
	 * @param cardPoint the cardinal points
	 * @param player to add
	 * @throws NoSuchZoneException
	 */
	public void movePlayerToNeighbour(CardinalPoints cardPoint,Player player) throws NoSuchZoneException {
		this.getNeighbour(cardPoint).addPlayer(player);
		this.removePlayer(player);
	}
	/**
	 * add the zombie to neighbour zone
	 * @param cardPoint the cardinal point
	 * @param zombie to add
	 * @throws NoSuchZoneException
	 */
	public void moveZombieToNeighbour(CardinalPoints cardPoint,Zombie zombie) throws NoSuchZoneException {
		if(this.isDoorOpen(cardPoint)){
			this.getNeighbour(cardPoint).addZombie(zombie);
			this.removeZombie(zombie);
		}
	}

	/**
	 * add an equipment to the zone
	 * @param equip the equipment to add
	 */
	public void addEquipment(Equipment equip) {
		this.equipments.add(equip);
	}
	
	/**
	 * remove an equipment from the zone
	 * @param e the equipment to remove
	 */
	public void removeEquipment(Equipment e) {
		this.equipments.remove(e);
	}
	
	/**
	 * get the equipment of the zone
	 * @return the equipment
	 */
	public List<Equipment> getEquipment(){
		return this.equipments;
	}
	/**
	 * return if the zone is searchable
	 * @return true if the zone is searchable,false otherwise
	 */
	public boolean isSearchable() {
		return this.searchable;
	}
	
	/**
	 * return if the player can attck in the zone
	 * @retunr true if the player can attck in the zone,false otherwise
	 */
	public boolean doesAllowAttack() {
		return this.allowAttack;
	}

	/**
	 * give the description of the zone with players, zombies znd open doors
	 * @return the description of the zone
	 */
	public String getDescription() {
		String ret="";
		ret +=this.name;
		ret+=" zombies: ";
		if(!(getZombies().isEmpty())) {
			ret+=getZombies().size();
		}
		else {
			ret+="0 ";
		}
		ret+=", survivors: ";
		if(!(getPlayers().isEmpty())) {
			ret+=getPlayers().size();
		}
		else {
			ret+="0 ";
		}
		ret+=", North Door: ";
		if(this.isDoorOpen(CardinalPoints.NORTH)) {
			ret+="Open, ";
		}
		else {
			ret+="Closed, ";
		}
		ret+="South Door: ";
		if(this.isDoorOpen(CardinalPoints.SOUTH)) {
			ret+="Open, ";
		}
		else {
			ret+="Closed, ";
		}
		ret+="East Door: ";
		if(this.isDoorOpen(CardinalPoints.EAST)) {
			ret+="Open, ";
		}
		else {
			ret+="Closed, ";
		}
		ret+="West Door: ";
		if(this.isDoorOpen(CardinalPoints.WEST)) {
			ret+="Open, ";
		}
		else {
			ret+="Closed, ";
		}
		return ret+"\n";
	}

	/**
	 * get the closed doors of the zone
	 * @return the list of closed doors
	 */
	public List<CardinalPoints> getClosedAndOpenableDoors(){
		List<CardinalPoints> closedDoors = new ArrayList<>();
		for(Map.Entry<CardinalPoints, Door> entry: this.getDoors().entrySet()) { 
			if(!entry.getValue().isOpen() && entry.getValue().getCanOpen()) {
				closedDoors.add(entry.getKey());
			}
		}
		return closedDoors;
	}
	

	/**
	 * get the opened doors of the zone
	 * @return the list of opened doors
	 */
	public List<CardinalPoints> getOpenedDoors(){
		List<CardinalPoints> openedDoors = new ArrayList<>();
		for(Map.Entry<CardinalPoints, Door> entry: this.getDoors().entrySet()) { 
			if(entry.getValue().isOpen()) {
				openedDoors.add(entry.getKey());
			}
		}
		return openedDoors;
	}


	/**
	 * create the string of the zone
	 */
	public String toString(){
		String ret="";
		//first raw
		ret +=this.name;
		if(!(getZombies().isEmpty())) {
			ret+="z";
			ret+=getZombies().size();
		}
		else {
			ret+="  ";
		}
		//second raw
		if(!(getPlayers().isEmpty())) {
			ret+=" s";
			ret+=getPlayers().size();
		}
		else {
			ret+="   ";
		}
		return ret;
	}
	/**
	 * turn to default noise which is 0 usefull for the end of each rounds
	 */
	public void turnToDefaultNoiseInzoneZone() {
		this.noise.turnToDefaultNoise();
	}

	/**
	 * give direction to go from this zone to zone z
	 * @param z the zone to go to
	 * @return the direction to go to zone z
	 */
	public CardinalPoints inWhichDirectionIs(Zone z) {
		CardinalPoints cardPointV = this.position.inWhichVerticalDirectionIs(z.getPosition());
		CardinalPoints cardPointH = this.position.inWhichHorizontalDirectionIs(z.getPosition());
		if (cardPointV != null && this.isDoorOpen(cardPointV)){
			return cardPointV;
		}
		else if (cardPointH != null && this.isDoorOpen(cardPointH)){
			return 	cardPointH;
		}
		else {
			return null;
		}
	}
	
	
}
