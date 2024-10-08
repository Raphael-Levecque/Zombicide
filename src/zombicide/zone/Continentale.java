package zombicide.zone;

import zombicide.board.Board;
import zombicide.util.CardinalPoints;

/**
 * Class of Continentale
 */
public class Continentale extends Building {
	
	/**
	 * Constructor of Continentale
	 * @param p position
	 * @param b the board
	 */
	public Continentale(Position p,Board b) {
		super(p,b);
		this.name="C";
		this.searchable = false;
		this.allowAttack=false;
		
	}
	
	/**
	 * @return the name of the zone
	 */
	public String toString(){
		return this.name+"     ";
	}
	
	/**
	 * give the description of the zone with players, zombies znd open doors
	 * @return the description of the zone
	 */
	public String getDescription() {
		String ret="";
		ret +=this.name;
		ret+=" zombies: unknown";
		ret+=", survivors: unknown";
		
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
}
