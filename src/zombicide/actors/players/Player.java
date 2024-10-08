package zombicide.actors.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombicide.actions.Action;
import zombicide.actors.Actors;
import zombicide.actors.zombie.Zombie;
import zombicide.equipments.Equipment;
import zombicide.equipments.Pistol;
import zombicide.exception.NoSuchZoneException;
import zombicide.listchooser.RandomListChooser;
import zombicide.util.CardinalPoints;
import zombicide.zone.doors.Door;
import zombicide.actors.players.util.Level;

/** Class of Player */
public abstract class Player extends Actors {

	/**
	 * the max size of the bag
	 */
	private static final int MAX_BAG_SIZE = 5;
	/**
	 * the number of dice
	 */
	private static final int DICE = 7;
	/**
	 * the equipement the player has
	 */
	protected List<Equipment> bag;
	/**
	 * the equipment the player has in his hand
	 */
	protected Equipment inhand;
	/**
	 * how many shot the player has 
	 */
	protected int throwThimbleBonus;
	/**
	 * the number of expertise points
	 */
	protected int expertisePoints;
	
	/*States if player is snoop*/
	protected boolean snoop;
	
	/*States if player is healer*/
	protected boolean healer;
	
	/*States if player is lucky*/
	protected boolean lucky;
	
	/* level of the player */
	protected Level level;
	
	/* The name of the game */
	protected String gameName;

	
	/**
	 * Build a Player 
	 */
	public Player() {
		super();
		this.actionPoints=3;
		this.currentActionPoints = this.actionPoints;
		this.lifePoints=5;
		this.throwThimbleBonus=0;
		this.expertisePoints=0;
		this.snoop = false;
		this.healer = false;
		this.lucky = false;
		this.bag = new ArrayList<Equipment>();
		this.gameName = null;
		this.initPistol();
		this.level = Level.Init;
	}

	/**
	 * get the thimble 
	 * @return the thimble
	 */
	public int getThrowThimbleBonus() {
		return this.throwThimbleBonus;
	}
	
	/**
	 * set a pistol in hand
	 */
	protected void initPistol() {
		Equipment pistol = new Pistol();
		pistol.setPlayer(this);
		this.setInhand(pistol);
	}

	/**
	 * @return the current action points of this Player
	 */
	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	/**
	 * Set the current action points to this player
	 * @param currentActionPoints 
	 */
	public void setCurrentActionPoints(int currentActionPoints) {
		this.currentActionPoints = currentActionPoints;
	}

	/**
	 * return if the player is a snoop
	 * @return true if the player is a snoop, false otherwise
	 */
	public boolean isSnoop() {
		return this.snoop;
	}
	/**
	 * return if the player is a healer
	 * @return true if the player is a healer, false otherwise
	 */
	public boolean isHealer() {
		return this.healer;
	}
	/**
	 * return if the player is a lucky
	 * @return true if the player is a lucky, false otherwise
	 */
	public boolean isLucky() {
		return this.lucky;
	}

	/**
	 * move the player
	 * @throws NoSuchZoneException
	 */
	public void move() throws NoSuchZoneException {
		List<CardinalPoints> l = this.zone.getOpenedDoors();
		CardinalPoints cardinalPoint = chooseCardinalPoints(l);
		if(cardinalPoint != null) {
			this.zone.movePlayerToNeighbour(cardinalPoint, this);
		}
		else {
			System.out.println("No direction to move");
		}

	}
	
	/**
	 * get the expertise points
	 * @return the expertise points
	 */
	public int getExpertisePoints() {
		return this.expertisePoints;
	}

	/**
	 * set the expertise points
	 * @param expertisePoints the expertise points
	 */
	public void setExpertisePoints(int expertisePoints) {
		this.expertisePoints = expertisePoints;
	}

	/**
	 * get the list of equipements
	 * @return the list of equipments
	 */
	public List<Equipment> getBag() {
		return bag;
	}
	/**
	 * set the bag of equipment to bag
	 * @param bag the list of equipment to set
	 */
	public void setBag(List<Equipment> bag) {
		this.bag = bag;
	}
	/**
	 * get the equipment in hand
	 * @return the equipment in hand
	 */
	public Equipment getInhand() {
		return inhand;
	}
	/**
	 * set the equipment to be in hand
	 * @param e to be set in hand
	 */
	public void setInhand(Equipment e) {
		this.bag.remove(e);
		if (this.inhand != null) {
			this.bag.add(this.inhand);
		}
		this.inhand = e;
	}
	/**
	 * set the thimble bonus
	 * @param throwThimbleBonus to set
	 */
	public void setThrowThimbleBonus(int throwThimbleBonus) {
		this.throwThimbleBonus = throwThimbleBonus;
	}
	/**
	 * add in bag the equipment e
	 * @param e the equipment to be added
	 */
	public void addInBag(Equipment e) {
		this.zone.removeEquipment(e);
		e.setPlayer(this);
		this.bag.add(e);
	}
	
	/**
	 * set the game's name
	 * @param name
	 */
	public void setGameName(String name) {
		this.gameName = name+" ("+this.getClass().getSimpleName()+")";
	}
	
	/**
	 * get the game's name
	 * @return the game's name
	 */
	public String getGameName() {
		return this.gameName;
	}
	
	
	/**
	 * true if the player has something in hand able to open a door and there is a closed door in the zone, false otherwise
	 * @return true if the player has something in hand able to open a door and there is a closed door in the zone, false otherwise
	 */
	public boolean canOpenDoor() {
		if(this.inhand == null) {
			return false;
		}
		else {
			return this.inhand.isAbleToOpenADoor() && this.zone.getClosedAndOpenableDoors().size() != 0 ;
		}		
	}
	
	/**
	 * true if the player can move, false otherwise
	 * @return true if the player can move, false otherwise
	 */
	public boolean canMove() {
		return !this.getZone().getOpenedDoors().isEmpty();
	}
	
	/**
	 * true if the player can take an equipment in hand, false otherwise
	 * @return true if the player can take an equipment in hand, false otherwise
	 */
	public boolean canTakeInHand() {
		return !this.getBag().isEmpty();
	}
	
	/**
	 * true if the player can use an equipment, false otherwise
	 * @return true if the player can use an equipment, false otherwise
	 */
	public boolean canUseEquipment() {
		if(this.inhand == null) {
			return false;
		}
		else {
			return this.inhand.isUtility();
		}
	}
	
	/**
	 * true if the player can attack, false otherwise
	 * @return true if the player can attack, false otherwise
	 */
	public boolean canAttack() {
		if(this.inhand == null) {
			return false;
		}
		else {
			return this.getInhand().isWeapon() && this.zone.doesAllowAttack() && !this.inhand.getReachableZombie().isEmpty(); 
		}
	}
	
	/**
	 * true if the player can search, false otherwise
	 * @return true if the player can search, false otherwise
	 */
	public boolean canSearch() {
		return this.zone.isSearchable() && !this.getEquipmentsInZone().isEmpty();
	}
	
	/**
	 * take from the bag to put it in hand
	 * @return true if the equipment was taken, false otherwise
	 */
	public void takeFromTheBag() {
		Equipment chosen = chooseEquipment(this.bag);
		if(chosen != null) {
			this.bag.remove(chosen);
			setInhand(chosen);
			System.out.println(this.gameName+" has taken "+chosen+"\n");
		}
		else {
			System.out.println("No equipment to take in hand");
		}
	}
	/**
	 * heal
	 */
	public void heal() {
		if(this.isHealer()) {
			Player p = this.choosePlayer(this.getPlayersOfZone());
			p.setLifePoints(p.getLifePoints()+1);
			System.out.println(p+" has been healed by "+this);
		}
	}
	
	/**
	 * take damage of i 
	 * @param dmg the damage
	 */
	public boolean takeDamage(int dmg) {
		this.lifePoints = this.lifePoints-dmg;
		if(this.getLifePoints()<1) {
			this.die();
			return true;
		}
		return false;
	}
	/**
	 * kill the player
	 */
	public void die() {
		zone.addEquipment(getInhand());
		this.inhand = null;
		while(!(this.bag.isEmpty())){
			zone.addEquipment(this.bag.get(0));
			this.bag.remove(0);
		}
		zone.removePlayer(this);
	}
	/**
	 * search the zone where the player is.
	 * @return liste of equipment
	 */
	public void search(){
		Equipment chosenEquip = this.chooseEquipment(this.getEquipmentsInZone());
		if(chosenEquip != null) {
			if(this.getNumberOfEquipmentsInBag() == MAX_BAG_SIZE) {
				System.out.println("Your bag is full, you have to drop an equipment\n");
				Equipment droppedEquip = this.chooseEquipment(this.getBag());
				this.drop(droppedEquip);
				System.out.println(this.gameName+" has dropped "+droppedEquip+"\n");
				this.addInBag(chosenEquip);
			}
			else {
				this.addInBag(chosenEquip);
			}
		}
		else {
			System.out.println("No equipment to get");
		}
	}
	
	public void useEquipment() {
		if(this.inhand != null) {
			this.inhand.use();
			this.inhand.unsetPlayer();
			this.inhand = null;
		}
	}

	/**
	 * look around the player
	 */
	public void lookAround() {
		System.out.println(zone.getDescription());
	}

	/**
	 * open door 
	 */
	public void openDoor(){
		List<CardinalPoints> closedDoors =this.zone.getClosedAndOpenableDoors();
		Door chosenDoor = chooseDoor(closedDoors);
		if(chosenDoor != null) {
			openDoor(chosenDoor);
		}
		else {
			System.out.println("No door to open");
		}

	}

	/**
	 * open door 
	 * @param d the door to open
	 */
	public void openDoor(Door d){
		d.openWith(this.getInhand());
	}

	/**
	 * make noise in a zone
	 */
	public void makeNoise() {
		this.zone.increaseNoise();
	}


	/**
	 * get number of equipments in the bag
	 * @return the number of equipments in the bag
	 */
	public int getNumberOfEquipmentsInBag() {
		int x=0;
		for (Equipment e : this.bag) {
			x++;
		}
		return x;
	}

	/**
	 * get the list of equipments in the zone
	 * @return the list of equipments in the zone
	 */
	public List<Equipment> getEquipmentsInZone() {
		return this.zone.getEquipment();
	}

	/**
	 * drop an equipment
	 * @param e the equipment to drop
	 */
	public void drop(Equipment e) {
		this.zone.addEquipment(e);
		this.bag.remove(e);
		e.unsetPlayer();
	}

	/**
	 * decrease the action points
	 * @param i the number of action points to decrease
	 */
	public void decreaseActionPoints(int i) {
		this.currentActionPoints=this.currentActionPoints-i;
	}
	/**
	 * intialisation the current action points with actions points
	 */
	public void reinitActionPoints() {
		this.currentActionPoints = this.actionPoints;
	}
	/**
	 * make a choice of a player
	 * @param player the list of player
	 * @return the chosen player
	 */
	public Player choosePlayer (List<Player> players) {
		RandomListChooser<Player> randChoice= new RandomListChooser<>();
		Player chosenElement = randChoice.choose("  Which player do you choose ?", players);
		System.out.println("  You've chosen : " +chosenElement.getGameName()+" "+chosenElement+"\n");
		return chosenElement;
	}

	/**
	 * make a choice of a zombie 
	 * @param zombies the list of zombie
	 * @return the chosen zombie
	 */
	public Zombie chooseZombie (List<Zombie> zombies) {
		RandomListChooser<Zombie> randChoice= new RandomListChooser<>();
		Zombie chosenElement = randChoice.choose("  Which zombie do you choose ?", zombies);
		if(chosenElement != null) {
			System.out.println("  You've chosen : " + chosenElement+"\n");
		}
		return chosenElement;
	}

	/**
	 * make a choice of a door
	 * @param closedDoors the list of closed doors
	 * @return the chosen door
	 */
	public Door chooseDoor (List<CardinalPoints> closedDoors) {
		RandomListChooser<CardinalPoints> randChoice= new RandomListChooser<>();
		CardinalPoints chosenElement = randChoice.choose("  Which door do you choose ?", closedDoors);
		System.out.println("  You've chosen : " + chosenElement+"\n");
		return this.zone.getDoor(chosenElement);
	}

	/**
	 * make a choice of a equipment
	 * @param equipments the list of equipment
	 * @return the chosen equipment
	 */
	public Equipment chooseEquipment (List<Equipment> equipments ) {
		RandomListChooser<Equipment> randChoice= new RandomListChooser<>();
		Equipment chosenElement = randChoice.choose("  Which equipment do you choose ?", equipments);
		System.out.println("  You've chosen : " + chosenElement+"\n");
		return chosenElement;
	}

	/**
	 * make a choice of a cardinal point
	 * @param cardinalPoints the list of cardinal points
	 * @return the chosen cardinal point
	 */
	public CardinalPoints chooseCardinalPoints (List<CardinalPoints> cardinalPoints) {
		RandomListChooser<CardinalPoints> randChoice= new RandomListChooser<>();
		CardinalPoints chosenElement = randChoice.choose("  Which direction do you choose ?", cardinalPoints);
		System.out.println("  You've chosen : " + chosenElement+"\n");
		return chosenElement;
	}
	/**
	 * make a choice of action
	 * @param actions lc the list chooser
	 */
	public void chooseAction (List<Action> actions) {
		RandomListChooser<Action> randChoice= new RandomListChooser<>();
		Action chosenElement = randChoice.choose("  Which action do you choose ?", actions);
		System.out.println("  You've chosen : " + chosenElement+"\n");
		chosenElement.use(this);
	}
	
	/**
	 * get the players of the zone
	 * @return the players of the zone
	 */
	public List<Player> getPlayersOfZone(){
		return this.zone.getPlayers();
	}
	/**
	 * get the the closed door of the zone
	 * @return the closed of the zone
	 */
	public List<CardinalPoints> getClosedAndOpenableDoors(){
		return this.zone.getClosedAndOpenableDoors();
	}
	/**
	 * throw the dice
	 * @return the value of the dice
	 */
	public int throwDice() {
		Random dice = new Random();
		return dice.nextInt(DICE) + this.throwThimbleBonus;
	}
	/**
	 * get the zombie of the zone of player
	 */
	public List<Zombie> getZombieOfZone(){
		return this.zone.getZombies();
	}
	/**
	 * attack a zombie
	 */
	public void attack() {
		Equipment inHand = this.inhand;
		List<Zombie> listZombie = inHand.getReachableZombie();
		Zombie chosenZombie = this.chooseZombie(listZombie);
		if(chosenZombie != null) {
			if(inHand.attack()) {
				boolean dead = chosenZombie.takeDamageFrom(inHand);
				if(dead) {
					this.updateLevel();
				}
			}
			System.out.println(chosenZombie+"\n");
		}
		else {
			System.out.println("No Zombie to kill");
		}
	}
	
	/**
	 * informations about player
	 */
	public String toString() {
		String bag =", bag = ";
		for(int i = 0;i<this.bag.size();i++) {
			bag+=this.bag.get(i)+", ";
		}
		String res = "lifePoints="+this.lifePoints+", experiencePoints="+this.expertisePoints+" ,inHand=";
		if(this.inhand == null) {
			res += "nothing";
		}
		else {
			res += this.inhand;
		}
		if(this.bag.size()!=0) {
			res = res + bag;
		}
		return res;
	}
	

	
	/**
	 * update the player's level
	 */
	public void updateLevel() {
		this.expertisePoints = this.expertisePoints + 1;
		if(this.level==Level.Init && this.expertisePoints>=Level.First.getValue()) {
			this.level = Level.First;
			this.actionPoints++;
		}
		else if(this.level==Level.First && this.expertisePoints>=Level.Second.getValue()) {
			this.level = Level.Second;
			this.actionPoints++;
		}
		else if(this.level==Level.Second && this.expertisePoints>=Level.Last.getValue()) {
			this.level = Level.Last;
			this.actionPoints++;
		}
	}

	/**
	 * get the level of the player
	 * @return the level of the player
	 */
	public Level getLevel() {
		return this.level;
	}
	
}
