package zombicide.util;

/**
 * the noise defined by the noise level
 */
public class Noise {
	/**
	 * the noise level
	 */
	protected int noiseLevel;
	
	/**
	 * Constructor of Noise
	 */
	public Noise() {
		this.noiseLevel=0;
	}

	/**
	 * @return the noise level
	 */
	public int getNoiseLevel() {
		return this.noiseLevel;
	}

	/**
	 * set the noise level 
	 * @param noiseLevel
	 */
	public void setNoiseLevel(int noiseLevel) {
		this.noiseLevel = noiseLevel;
	}

	/**
	 * turn the noise to default
	 */
	public void turnToDefaultNoise() {
		this.noiseLevel = 0;
	}
}
