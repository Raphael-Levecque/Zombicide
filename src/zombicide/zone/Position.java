package zombicide.zone;


import zombicide.util.CardinalPoints;

/**
 * a position defined by the x and y coordinate 
 */
public class Position {
	
	/**
	 * the x coordinate
	 */
	protected int x;
	/**
	 * the y coordinate
	 */
	protected int y;
	
	/**
	 * creates a position corresponding to given coordinates
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}
	/**
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * two positions are equals if they haave same coordinates
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position other = (Position) o;
			return this.x == other.x && this.y == other.y;
		} else {
			return false;
		}
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "("+this.x +","+this.y+")";
	}

	/**
	 * give vertical direction to go from this position to position p
	 * @param p the position to go to
	 * @return the direction to go to position p
	 */
	public CardinalPoints inWhichVerticalDirectionIs(Position p) {
		if(p.getY()>this.y){
			return CardinalPoints.SOUTH;
		}
		else if (p.getY()<this.y) {
			return CardinalPoints.NORTH;	
		}	
		return null;
	}

	/**
	 * give horizontal direction to go from this position to position p
	 * @param p the position to go to
	 * @return the direction to go to position p
	 */
	public CardinalPoints inWhichHorizontalDirectionIs(Position p) {
		if(p.getX()>this.x){
			return CardinalPoints.EAST;
		}
		else if (p.getX()<this.x) {
			return CardinalPoints.WEST;	
		}	
		return null;
	}

}
