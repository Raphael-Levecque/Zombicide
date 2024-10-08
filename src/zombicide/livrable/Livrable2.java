package zombicide.livrable;

import java.util.ArrayList;

import zombicide.actions.Move;
import zombicide.actors.players.Fighter;
import zombicide.actors.players.Healer;
import zombicide.actors.players.Lucky;
import zombicide.actors.players.Snoop;
import zombicide.actors.zombie.Walker;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.equipments.BottleOfHealing;
import zombicide.equipments.Equipment;
import zombicide.equipments.Map;
import zombicide.util.CardinalPoints;
import zombicide.zone.Zone;


public class Livrable2 {

    public static void usage(){
        System.out.println("Use : java -jar Livrable2.jar <width> <length>");
        System.out.println("Where <width> and <length> describre the dimension of the board");
        System.exit(0);
    }

    public static void main(String[] args) {
        /**- créer la ville d'entraînement,
        - placer un zombie (peu importe le type) sur chaque zone,
        - créer un survivant de chaque rôle sur le carrefour principal,
        - mettre une carte dans le sac à dos de chaque survivant,
        - mettre dans la main de chaque survivant une fiole,
        - afficher une représentation de la ville,
        - déplacer tous les survivants d'une case vers le nord
        - afficher sa représentation. */

        Board b = new TrainingBoard();
        System.out.println("Training Board");
        for (int i=0; i<b.getHeight(); i++) {
            for (int j=0; j<b.getWidth(); j++) {
                Zone z = b.getZone(i, j);
                z.addZombie(new Walker());
            }
        }
        Zone w=b.getMainCrossRoad();
        Snoop s = new Snoop();
        w.addPlayer(s);
        Lucky l = new Lucky();
        w.addPlayer(l);
        Healer h = new Healer();
        w.addPlayer(h);
        Fighter f = new Fighter();
        w.addPlayer(f);

        Map m1 = new Map(b);
        Map m2 = new Map(b);
        Map m3 = new Map(b);
        Map m4 = new Map(b);
        BottleOfHealing f1 = new BottleOfHealing();
        BottleOfHealing f2 = new BottleOfHealing();
        BottleOfHealing f3 = new BottleOfHealing();
        BottleOfHealing f4 = new BottleOfHealing();
        ArrayList<Equipment> bag1 = new ArrayList<Equipment>();
        ArrayList<Equipment> bag2 = new ArrayList<Equipment>();
        ArrayList<Equipment> bag3 = new ArrayList<Equipment>();
        ArrayList<Equipment> bag4 = new ArrayList<Equipment>();
        bag1.add(m1);
        bag2.add(m2);
        bag3.add(m3);
        bag4.add(m4);
        s.setBag(bag1);
        l.setBag(bag2);
        h.setBag(bag3);
        f.setBag(bag4);
        s.setInhand(f1);
        l.setInhand(f2);
        h.setInhand(f3);
        f.setInhand(f4);
        Move move = new Move();

        System.out.println(b.toString());

        try {
        	move.use(s);
            move.use(f);
            move.use(h);
            move.use(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(b.toString());
        
        

        


    }
}