package zombicide.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zombicide.util.Noise;

class NoiseTest {
	
	private Noise n;
	@org.junit.jupiter.api.BeforeEach
	public void beforeEach() {
		this.n = new Noise();
	}
	
	@Test
	public void initializationNoiseAt0() {
		assertEquals(0,n.getNoiseLevel());
	}
	

}
