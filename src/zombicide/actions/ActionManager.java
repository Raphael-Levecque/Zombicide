package zombicide.actions;

import java.util.ArrayList;
import java.util.List;

import zombicide.actors.players.Player;

/** Class of action manager*/
public class ActionManager {
	/**
	 * the list of actions
	 */
	protected List<Action> actions;
	/**
	 * Builds an ActionManager
	 */
	public ActionManager() {
		this.actions = new ArrayList<>();
	}
	/**
	 * add action to the list
	 * @param action to be added
	 */
	public void addAction(Action action) {
		this.actions.add(action);
	}
	/**
	 * get the doable action
	 * @param p the player
	 * @return the list of doable action
	 */
	public List<Action> getDoableActions(Player p) {
		List<Action> doable = new ArrayList<>();
		for(Action a : this.actions) {
			if(a.doable(p)) {
				doable.add(a);
			}
		}
		return doable;
	}

}
