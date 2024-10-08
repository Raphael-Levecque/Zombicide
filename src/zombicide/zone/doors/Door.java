package zombicide.zone.doors;

import zombicide.equipments.Equipment;

/** Class of Door */
public class Door{

    /**
     * the possibility to open the door
     */
	protected boolean canOpen;
	
    /**
     * the state of the door
     */
    private boolean open;


    /**
     * constructor for the door
     */
    public Door(boolean canOpen){
        this.open = false;
        this.canOpen = canOpen;
    }
    
    /**
     * @return the possibility to open the door
     */
    public boolean getCanOpen() {
    	return this.canOpen;
    }

    /**
     * @return the state of the door
     */
    public boolean isOpen(){
        return open;
    }

    /**
     * open the door
     */
    public void open(){
    	if(this.canOpen) {
    		open = true;
    	}
    }
    /**
     * open the door with equip
     * @param equip
     */
    public void openWith(Equipment equip) {
    	if(equip.isAbleToOpenADoor()) {
    		this.open();
    	}
    }
}
