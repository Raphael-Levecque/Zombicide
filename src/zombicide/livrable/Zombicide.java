package zombicide.livrable;

import zombicide.Game;
import zombicide.actors.players.Fighter;
import zombicide.board.Board;
import zombicide.exception.NoSuchZoneException;

public class Zombicide {
	
	private static final int MIN_WIDTH = 5;
	private static final int MIN_HEIGHT = 5;
	private static final int MIN_PLAYER = 2;
	
	
	private static void usage() {
		System.out.println("Usage : zombicide.jar <width> <height> <nbOfPlayers>");
		System.out.println("With <width> >= 5, <height> >= 5 and <nbOfPlayers> >= 2");
		System.out.println("If <width> == 5 and <height> == 5 then game will have a TrainingBoard");
		System.exit(0);
	}

	public static void main(String[] args) throws NoSuchZoneException {
		int width = 0;
		int height = 0;
		int nbPlayers = 0;
		
		if(args.length > 2 && Integer.parseInt(args[0]) >= Zombicide.MIN_WIDTH && Integer.parseInt(args[1]) >= Zombicide.MIN_HEIGHT && Integer.parseInt(args[2]) >= Zombicide.MIN_PLAYER) {
			width = Integer.parseInt(args[0]);
			height = Integer.parseInt(args[1]);
			nbPlayers = Integer.parseInt(args[2]);
		}
		else {
			Zombicide.usage();
		}

		Game g = new Game(width,height,nbPlayers);
		g.play();
	}

}

