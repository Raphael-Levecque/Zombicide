package zombicide.equipments;

/** Class of HealAction */
public abstract class Heal extends Utilities{

	/** the value of healing */
	protected int healValue;

	/**
	 * builds HealAction item
	 * @param healValue the value of healing
	 */
	public Heal(int healValue) {
		super(false);
		this.healValue = healValue;
	}
	/**
	 * get the value of healing
	 * @return
	 */
	public int getHealValue() {
		return healValue;
	}
	/**
	 * set the value of healing with given parameter
	 * @param healValue
	 */
	public void setHealValue(int healValue) {
		this.healValue = healValue;
	}
	
	/**
	 * use the heal item
	 */
	public abstract void use();
}
