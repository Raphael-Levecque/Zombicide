package zombicide.zone;
import java.util.*;

import zombicide.board.Board;
import zombicide.equipments.AidKit;
import zombicide.equipments.Axe;
import zombicide.equipments.Chainsaw;
import zombicide.equipments.Crowbar;
import zombicide.equipments.Equipment;
import zombicide.equipments.InfraredGlasses;
import zombicide.equipments.Map;
import zombicide.equipments.MasterKey;
import zombicide.equipments.Pistol;
import zombicide.equipments.Rifle;


/** Class of Building */
public class Building extends Zone {
	
	/**
	 * the number of people of the zone
	 */
	protected int peopleEntering ;
	
	/**
	 * build a building with given position
	 * @param p
	 * @param b
	 */
	public Building(Position p,Board b) {
		super(p,b);
		this.peopleEntering=0;
		this.name="B";
		this.equipments = new ArrayList<Equipment>();
		this.initEquipement();
	}

	/**
	 * @return the equipments
	 */
	public List<Equipment> getEquipments() {
		return equipments;
	}
	/**
	 * set the equipments
	 * @param e the list of equipment to set
	 */
	public void setEquipment(List<Equipment> e) {
		this.equipments=e;
	}
	/**
	 * add an equipment
	 * @param e the equipment to add 
	 */
	public void addEquipement(Equipment e) {
		this.equipments.add(e);
	}
	/**
	 * get the equipment 
	 * @param e the equipment to retrieve
	 * @return the equipment
	 */
	public Equipment getEquipment(Equipment e) {
		int index = this.equipments.indexOf(e);
		return this.equipments.get(index);
	}
	/**
	 * initialize the equipment
	 */
	protected void initEquipement() {
		Random rand = new Random();
		int nbEquipToAdd = rand.nextInt(4);
		List<Equipment> addableEquipments = this.generateAddableEquipments(nbEquipToAdd);
		for(int i = 0;i<nbEquipToAdd;i++) {
			Equipment e = addableEquipments.get(rand.nextInt(addableEquipments.size()));
			this.addEquipement(e);
			addableEquipments.remove(e);
		}
	}
	
	/**
	 * generate the addable equipment
	 * @return the list of addable equipment
	 */
	protected List<Equipment> generateAddableEquipments(int nbEquipToAdd) {
		List<Equipment> addableEquipments = new ArrayList<Equipment>();
		for(int i = 0; i < nbEquipToAdd; i++) {
			addableEquipments.add(new Axe());
			addableEquipments.add(new Rifle());
			addableEquipments.add(new Pistol());
			addableEquipments.add(new InfraredGlasses());
			addableEquipments.add(new Map(this.board));
			addableEquipments.add(new Crowbar());
			addableEquipments.add(new AidKit());
			addableEquipments.add(new MasterKey());
			addableEquipments.add(new Chainsaw());
		}
		return addableEquipments;
	}
	
	
}
