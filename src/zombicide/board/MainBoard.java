package zombicide.board;

/** Class of mainBoard */
public class MainBoard{


    public static void main(String[] args) {
        Board b = new TrainingBoard();
        Board a = new ClassicalBoard(12,10);
        System.out.println(a.toString());
    }
}