package zombicide.equipments;

import zombicide.board.Board;

/** Class of Map */
public class Map extends Utilities{

	/** the board */
	private Board board;

	/**
	 * Constructor of Map
	 * @param board the board
	 */
	public Map(Board board){
		super(false);
		this.board = board;
	}
	/**
	 * show the map
	 */
	public void use() {
		System.out.println(board);
		this.player.makeNoise();
	}
}

