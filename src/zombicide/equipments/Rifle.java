package zombicide.equipments;

/** Class of Rifle */
public class Rifle extends Weapon{

	/** Constructor of Rifle */
	public Rifle() {
		super(false, 1,4,1,3,false);
		this.setMinimalReach(1);
	}
}
