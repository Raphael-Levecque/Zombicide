package zombicide.actors.players.util;

/** Enumeration of the levels */
public enum Level {

	/** Enumeration of the levels */
	Init(0),First(3),Second(7),Last(11);

	/** The value of the level */
	private final int value;

	/**
	 * Constructor of the level
	 * @param i the value of the level
	 */
	Level(int i) {
		this.value = i;
	}

	/**
	 * Get the value of the level
	 * @return the value of the level
	 */
	public int getValue() {
		return value;
	}
}
