package zombicide.zone;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import zombicide.actors.players.Lucky;
import zombicide.actors.players.Player;
import zombicide.actors.zombie.Zombie;
import zombicide.board.Board;
import zombicide.board.TrainingBoard;
import zombicide.exception.NoSuchZoneException;
import zombicide.zone.Position;
import zombicide.zone.Zone;
import zombicide.zone.doors.Door;
import zombicide.util.CardinalPoints;

import org.junit.jupiter.api.Test;

class ZoneTest {
	
	private Zone zone;
	private Board board;
	private Player p;
	private Zombie z;
	private Position pos;
	private Position p2;
	private Zone secondZone;
	private Door d;
	
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.board = new TrainingBoard();
		this.p = new Lucky();
		this.z = new Zombie(0, 0, 0);
		this.pos = new Position(1,1);
		this.p2 = new Position(0,1);
		this.zone = new Zone(pos,board);
		this.secondZone = new Zone(p2,board);
		this.d = new Door(true);
	}
	@Test
	public void addPlayerTest() {
		assertEquals(0, this.zone.getPlayers().size());
		this.zone.addPlayer(p);
		assertEquals(1,this.zone.getPlayers().size());
	}
	@Test
	public void addZombieTest() {
		assertEquals(0,this.zone.getZombies().size());
		this.zone.addZombie(z);
		assertEquals(1,this.zone.getZombies().size());
	}
	@Test
	public void increaseNoiseTest() {
		assertEquals(0,this.zone.getNoiseLevel());
		this.zone.increaseNoise();
		assertEquals(1,this.zone.getNoiseLevel());
	}
	@Test
	public void removeNoiseTest() {
		assertEquals(0,this.zone.getNoiseLevel());
		this.zone.increaseNoise();
		assertEquals(1,this.zone.getNoiseLevel());
		this.zone.increaseNoise();
		assertEquals(2,this.zone.getNoiseLevel());
		this.zone.removeNoise();
		assertEquals(0,this.zone.getNoiseLevel());	
	}
	@Test
	public void removePlayerTest() {
		assertEquals(0, this.zone.getPlayers().size());
		this.zone.addPlayer(p);
		assertEquals(1,this.zone.getPlayers().size());
		this.zone.removePlayer(p);
		assertEquals(0, this.zone.getPlayers().size());
	}
	@Test
	public void removeZombieTest() {
		assertEquals(0,this.zone.getZombies().size());
		this.zone.addZombie(z);
		assertEquals(1,this.zone.getZombies().size());
		this.zone.removeZombie(z);
		assertEquals(0,this.zone.getZombies().size());
	}
	@Test
	public void getClosedDoorTest() {
		List<CardinalPoints> points = new ArrayList<>();
		points.add(CardinalPoints.NORTH);
		points.add(CardinalPoints.EAST);
		points.add(CardinalPoints.SOUTH);
		points.add(CardinalPoints.WEST);
		zone.addDoor(CardinalPoints.NORTH, d);
		zone.addDoor(CardinalPoints.SOUTH, d);
		zone.addDoor(CardinalPoints.EAST, d);
		zone.addDoor(CardinalPoints.WEST, d);
		assertEquals(4,zone.getClosedAndOpenableDoors().size());
		zone.openDoor(CardinalPoints.NORTH);
		zone.openDoor(CardinalPoints.SOUTH);
		zone.openDoor(CardinalPoints.EAST);
		zone.openDoor(CardinalPoints.WEST);
		assertEquals(0, zone.getClosedAndOpenableDoors().size());
	}
	@Test
	public void getOpenedDoorTest() {
		zone.addDoor(CardinalPoints.NORTH, d);
		zone.addDoor(CardinalPoints.EAST, d);
		zone.addDoor(CardinalPoints.SOUTH, d);
		zone.addDoor(CardinalPoints.WEST, d);
		List<CardinalPoints> points = new ArrayList<>();
		points.add(CardinalPoints.WEST);
		points.add(CardinalPoints.NORTH);
		points.add(CardinalPoints.SOUTH);
		points.add(CardinalPoints.EAST);
		assertEquals(0,zone.getOpenedDoors().size());
		zone.openDoor(CardinalPoints.NORTH);
		zone.openDoor(CardinalPoints.EAST);
		zone.openDoor(CardinalPoints.SOUTH);
		zone.openDoor(CardinalPoints.WEST);
		assertEquals(4, zone.getOpenedDoors().size());
	}
	@Test
	public void movePlayerToNeighbourTest() throws NoSuchZoneException{
		zone.addPlayer(p);
		zone.addNeighbour(secondZone, CardinalPoints.NORTH);
		assertEquals(1,zone.getPlayers().size());
		assertEquals(0,secondZone.getPlayers().size());
		zone.movePlayerToNeighbour(CardinalPoints.NORTH, p);
		assertEquals(0,zone.getPlayers().size());
		assertEquals(1,secondZone.getPlayers().size());
		
	}
	@Test
	public void moveZombieToNeighbourTest() throws NoSuchZoneException{
		zone.addZombie(z);
		zone.addNeighbour(secondZone, CardinalPoints.NORTH);
		zone.addDoor(CardinalPoints.NORTH, d);
		zone.openDoor(CardinalPoints.NORTH);
		assertEquals(1,zone.getZombies().size());
		assertEquals(0,secondZone.getZombies().size());
		zone.moveZombieToNeighbour(CardinalPoints.NORTH, z);
		assertEquals(0,zone.getZombies().size());
		assertEquals(1,secondZone.getZombies().size());
	}
	
}

