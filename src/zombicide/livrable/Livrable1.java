package zombicide.livrable;

import zombicide.board.Board;
import zombicide.board.ClassicalBoard;
import zombicide.board.TrainingBoard;

public class Livrable1 {

    public static void usage(){
        System.out.println("Use : java -jar Livrable1.jar <width> <length>");
        System.out.println("Where <width> and <length> describre the dimension of the board");
        System.exit(0);
    }

	public static void main(String[] args) {
		int width = 5;
		int length = 5;
		if (args.length < 2){
			Livrable1.usage();
		}
		else {
			width = Integer.parseInt(args[0]);
			length = Integer.parseInt(args[1]);
		}
		Board a = new ClassicalBoard(width,length);
		System.out.println("Classical board of width : "+args[0]+" and length of : "+args[1]);
		System.out.println(a.toString());
		Board b = new TrainingBoard();
		System.out.println("Training Board");
		System.out.println(b.toString());
	}

}
