package zombicide.livrable;

import zombicide.actions.ActionManager;
import zombicide.actors.players.Fighter;
import zombicide.actors.players.Healer;
import zombicide.actors.players.Lucky;
import zombicide.actors.players.Player;
import zombicide.actors.players.Snoop;
import zombicide.actors.zombie.Walker;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.Axe;
import zombicide.equipments.BottleOfHealing;
import zombicide.exception.NoSuchZoneException;
import zombicide.zone.Zone;
import zombicide.util.*;
import zombicide.actions.*;

import java.util.ArrayList;
import java.util.List;

public class Livrable3 {
	
	
public static void initZombie(Board b) {
    for(int i = 0; i<b.getHeight() ; i++) {
        for (int j=0; j<b.getWidth(); j++) {
            Zone z = b.getZone(i, j);
            z.addZombie(new Walker());
         }
     }
}

public static List<Player> initPlayers(Zone spawnZone) {
	List<Player> players = new ArrayList<>();
	players.add(new Snoop());
	players.add(new Lucky());
	players.add(new Healer());
	players.add(new Fighter());
	Livrable3.spawnPlayers(players, spawnZone);
	
	return players;
}
	
public static void spawnPlayers(List<Player> players, Zone spawnZone) {
	for(Player p: players) {
	    spawnZone.addPlayer(p);
	}
}

public static void initActions(ActionManager am, Board b) {
	am.addAction(new OpenDoor(b));
	am.addAction(new HealAction());
	am.addAction(new LookAround());
	am.addAction(new MakeNoise());
	am.addAction(new Move());
	am.addAction(new Search());
	am.addAction(new TakeInHand());
	am.addAction(new UseEquipment());
	am.addAction(new Attack());
}

public static void eachPlayerPlaysOnce(List<Player> players, ActionManager aM) {
	int i = 1;
	for(Player p: players) {
		System.out.println("Survivor "+i);
	    System.out.println(p);
	    List<Action> doable = aM.getDoableActions(p);
	    p.chooseAction(doable);
	    System.out.println(p);
	    
	}
}

public static void zombiesPlay(List<Zombie> zombies,Zone noisiestZone) throws NoSuchZoneException {
    for(int i=0;i<zombies.size();i++) {
 	   zombies.get(i).play(noisiestZone);
    }
}
	
public static void main(String[] args) throws NoSuchZoneException {
	
	

       Board board = new TrainingBoard();
       System.out.println("Training Board :");
       
       Livrable3.initZombie(board);
       
       Zone aboveMainCrossRoad =board.getMainCrossRoad().getNeighbour(CardinalPoints.NORTH);
       List<Player> players = Livrable3.initPlayers(aboveMainCrossRoad);
       
       System.out.println(board);
       
       Axe a = new Axe();
       BottleOfHealing boh = new BottleOfHealing();
       players.get(1).addInBag(a);
       players.get(2).addInBag(boh);
       players.get(1).setInhand(a);
       players.get(2).setInhand(boh);
       
       ActionManager actionManager = new ActionManager();
       Livrable3.initActions(actionManager,board);
       
       Livrable3.eachPlayerPlaysOnce(players, actionManager);
       
       System.out.println("Zombies");
       Zone noisiestZone = board.getNoisiestZone();
       
       List<Zombie> zombies = board.getAllZombies();
       Livrable3.zombiesPlay(zombies, noisiestZone);

       System.out.println(board);
       
	}
}

