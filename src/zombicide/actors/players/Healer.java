package zombicide.actors.players;

/** Class of Healer */
public class Healer extends Player{

	/**
	 * builds a Healer 
	 */
	public Healer() {
		super();
		this.healer = true;
	}

	/**
	 * heal 
	 */
	public void heal() {
		Player chosenPlayer = this.choosePlayer(this.getPlayersOfZone());
		chosenPlayer.addLifePoints(1);
	}
}
