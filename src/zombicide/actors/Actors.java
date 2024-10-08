package zombicide.actors;

import zombicide.zone.Zone;

/** Class of Actors */
public abstract class Actors {
	
	/** the zone of the actor */
	protected Zone zone;

	/** the action points of the actor */
	protected int actionPoints;

	/** the life points of the actor */
	protected int lifePoints;

	/* Current Actions points*/
	protected int currentActionPoints;
	
	/**
	 * Builds a player with the zone
	 * @param zone the zone the player is
	 */
	public Actors() {
		this.currentActionPoints = this.actionPoints;
	}

	/**
	 * @return the zone of the player
	 */
	public Zone getZone() {
		return zone;
	}

	/**
	 * @param zone the player is 
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
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
	 * @return the action points
	 */
	public int getActionPoints() {
		return actionPoints;
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
	 * set the action points
	 * @param actionPoints to set
	 */
	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}
	/**
	 * get the life points
	 * @return the life points
	 */
	public int getLifePoints() {
		return lifePoints;
	}
	/**
	 * set the life points
	 * @param lifePoints
	 */
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	/**
	 * add to the life points, the lifePoints
	 * @param lifePoints the points to be added
	 */
	public void addLifePoints(int lifePoints) {
		this.lifePoints = this.lifePoints + lifePoints ;
	}

	
}

