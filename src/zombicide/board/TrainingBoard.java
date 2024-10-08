package zombicide.board;

import java.util.ArrayList;
import java.util.List;

import zombicide.zone.Position;
import zombicide.zone.SpawnPoint;
import zombicide.zone.Zone;

/** Class of TrainingBoard */
public class TrainingBoard extends Board{
	
	/** width of the board */
	protected static final int width = 5;

	/** height of the board */
	protected static final int height = 5;
	
	/** Creates a new TrainingBoard */
	public TrainingBoard() {
		super(width, height);
	}
	
	/** 
	 * Initializes the sewers
	 */
	@Override
	protected void initSewers() {
		int xPos = this.mainCrossRoadPos.getX();
		int yPos = this.mainCrossRoadPos.getY();

		/*Main sewer */
		this.theZones[xPos][yPos] = new SpawnPoint(new Position(xPos,yPos),0,this);
	}
	
	/**
	 * get the spawn points
	 * @return the list of spawn points
	 */
	protected List<Zone> getSpawnPoints() {
		List<Zone> spawn = new ArrayList<>();
		spawn.add(this.theZones[this.mainCrossRoadPos.getX()][this.mainCrossRoadPos.getY()]);
		
		return spawn;
	}
}
