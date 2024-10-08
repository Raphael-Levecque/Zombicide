package zombicide.equipments;

import zombicide.util.CardinalPoints;
import zombicide.zone.Position;
import zombicide.zone.Zone;

/** Class of InfraredGlasses */
public class InfraredGlasses extends Utilities{
	
	/** Constructor of InfraredGlasses */
	public InfraredGlasses() {
		super(false);
	}

	/**
	 * see the board around
	 */
	public void use() {
		String r="";
		Zone z = player.getZone();
		Position p = player.getZone().getPosition();
		for(int y =p.getY()-1; y<p.getY()+1; y++){
			int cmpt =0;
			for(int o = 0; o<3; o++) {
				for(int x =p.getX()-1; x<p.getX()+1; x++){
					if(o==0) {
						if (z.isDoorOpen(CardinalPoints.NORTH)) {
							r += "    ";
						}
						else {
							r += "----";
						}
					}
					else {
						if (z.isDoorOpen(CardinalPoints.WEST)) {
							r += " ";
						}
						else {
							r += "|";
						}
						r += z.toString().substring(cmpt, cmpt+3);
					}
				}
			if (o!=0) {
				cmpt += 3;
			}
			r += "|";
			r += "\n";
			}
		}
		for(int q = p.getX()-1; q<p.getX()+1; q++) {
			r+= "----";
		}
		System.out.println(r);
	}
}