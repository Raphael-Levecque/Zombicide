package zombicide.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import zombicide.actors.players.Fighter;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.zone.Position;
import zombicide.zone.Zone;

public class MakeNoiseTest {
	private Position p;
	private Zone z;
	private Fighter c;
	private Board b;
	private MakeNoise makeNoise;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.b = new TrainingBoard();
		this.p = new Position(1,2);
		this.z = new Zone(p,b);
		this.c = new Fighter();
		this.makeNoise = new MakeNoise();
		z.addPlayer(c);
	}
	@Test
	public void useTest() {
		makeNoise.use(c);
		assertEquals(1, z.getNoiseLevel());
		assertEquals(2, c.getCurrentActionPoints());
	}
}
