package matter;

import java.util.*;
import allitems.*;

/**
 * 
 * The class that is a 2x2 grid map of a given planet object and holds all
 * information associated with objects on said planet. Such as Land Marks
 * and Outposts.
 *
 */

public class PlanetLocation extends Place{
	private Outpost currentOutpost;
	private LandMark currentLandMark;
	
	/**
	 * The constructor for the Planet Location class
	 * 
	 * @param tempCoordinateX the X Coordinate of the object on the planet;
	 * @param tempCoordinateY the Y Coordinate of the object on the planet;
//	 * @param tempName the name of the Object on the planet;
	 */
	public PlanetLocation(int tempCoordinateX, int tempCoordinateY, String tempName) {
		super(tempCoordinateX, tempCoordinateY, tempName);
	}
	
	public PlanetLocation(int tempCoordinateX, int tempCoordinateY) {
		super(tempCoordinateX, tempCoordinateY);
	}
	
	/**
	 * The coordinates can be translated into a 1D position grid.
	 * Therefore given coordinates, outposts said position.
	 * 
	 * @return the position on the planet's grid
	 */
	public int returnPosition() {
		return (2 * getCoordinateX()) + (getCoordinateY() + 1);
	}
	
	/**
	 * Given a position returns the X coordinate of the object on the planet.
	 * 
	 * @param position the given position.
	 * @return returns X coordinate.
	 */
	public static int returnCordX(int position) {
		int CordX = -1;
		if(position >= 1 && position <= 2) {
			CordX = 0;
		} else if (position >= 3 && position <= 4) {
			CordX = 1;
		} else {
			System.out.print("ERROR: CordX, This should not happen");
		}
		return CordX;
 	}
	
	/**
	 * Given a position returns the Y coordinate of the object on the planet.
	 * 
	 * @param position the given position.
	 * @return returns Y coordinate.
	 */
	public static int returnCordY(int position) {
		int CordY = -1;
		if(position == 1 || position == 3) {
			CordY = 0;
		} else if (position == 2 || position == 4) {
			CordY = 1;
		} else {
			System.out.print("ERROR: CordY, This should not happen");
		}
		return CordY;
	}
	
	/**
	 * Generates a random Fuel Canister Item and returns it.
	 * 
	 * @return Fuel Canister Item.
	 */
	public FuelCanister generateFuelCanister() {
		Random fuelChooser = new Random();
		FuelCanister fuel = null;
		int fuelID = fuelChooser.nextInt(3);
		switch(fuelID) {
			case 0: fuel = new FuelCanister("Jerry Can", 1000, 200, "Restores a small (1000) amount of fuel.");
					break;
			case 1: fuel = new FuelCanister("Tank", 2000, 350, "Restores a moderate (2000) amount of fuel.");
					break;
			case 2: fuel = new FuelCanister("'Larger-than-it-looks' canister", 5000, 700, "Restores a 'little?' (5000) amount of fuel.");
					break;
		}
		return fuel;
	}
	
	/**
	 * Generates a random Medical Item and returns it.
	 * 
	 * @return Medical Item.
	 */
	public MedicalItem generateMedicalItem() {
		Random healChooser = new Random();
		MedicalItem healingItem = null;
		int healID = healChooser.nextInt(3);
		switch(healID) {
			case 0: healingItem = new MedicalItem("Bandages", 25, 3, 200, "Out of sight, out of mind. Heals 25 HP, has 3 uses.", false);
					break;
			case 1: healingItem = new MedicalItem("Medical Kit", 50, 3, 500, "If swung hard enough, it can nullify any ongoing pain. Heals 50 HP, has 2 uses.", false);
					break;
			case 2: healingItem = new MedicalItem("Space Plague Cure", 10, 2, 400, "Can cure Space Plague faster than a ship being sucked into a black hole. Heals 10 HP, Cures space plague, has 2 uses.", true);
					break;
		}
		return healingItem;
	}
	
	/**
	 * Generates a random Food Item and returns it.
	 * 
	 * @return Food Item.
	 */
	public FoodItem generateFoodItem() {
		Random foodChooser = new Random();
		FoodItem foodItem = null;
		int foodID = foodChooser.nextInt(6);
		switch(foodID) {
			case 0: foodItem = new FoodItem("Milky Way", 15, 150, "Myth says the inside of the bar looks like a long lost galaxy. Restores 10 stamina.");
					break;
			case 1: foodItem = new FoodItem("Dark Hole Energy", 100, 600, "Who knows what it contains, surely it's good for you. Restores 100 stamina.");
					break;
			case 2: foodItem = new FoodItem("Earth Food", 20, 150, "Made from 100% pure Earth meat. Restores 20 stamina.");
					break;
			case 3: foodItem = new FoodItem("McFeelGood", 10, 100, "Served in 0.0001 nanoseconds by the galaxy's best robots. Restores 10 stamina.");
					break;
			case 4: foodItem = new FoodItem("Revenge", 150, 750, "Best served cold. Restores 150 stamina.");
					break;
			case 5: foodItem = new FoodItem("Aurora Noodles", 50, 400, "Brighter than opening your curtains in the morning.  Restores 50 stamina.");
			break;
		}
		return foodItem;
	}
	
	/**
	 * Generates a random Crew Item and returns it.
	 * 
	 * @return Crew Item.
	 */
	public CrewItem generateCrewItem() {
		Random specialChooser = new Random();
		CrewItem crewItem = null;
		int crewID = specialChooser.nextInt(4);
		switch(crewID) {
			case 0: crewItem = new CrewItem("Wrench", 1500, "Repair", "Rusty, holding onto it would make someone look more professional. (Repair +1)");
					break;
			case 1: crewItem = new CrewItem("Pair of Shades", 1500, "Charisma", "Makes everything else look cooler than you do. (Charisma +1)");
					break;
			case 2: crewItem = new CrewItem("Metal Detector", 1500, "Scavenging", "May help you find bountiless treasure (friendship). (Scavenging +1)");
					break;
			case 3: crewItem = new CrewItem("Alien Goo", 1500, "Healer", "Applying this bright green glue is a questionable subsitute for gloves. (Healer +1)");
					break;
		}
		return crewItem;
	}
	
	public Outpost getCurrentOutpost() {
		return currentOutpost;
	}
	public void setCurrentOutpost(Outpost tempOutpost) {
		currentOutpost = tempOutpost;
	}
	public LandMark getCurrentLandMark() {
		return currentLandMark;
	}
	public void setCurrentLandMark(LandMark tempLandMark) {
		currentLandMark = tempLandMark;
	}
	public String toString() {
	return "Your position is: " + Integer.toString((returnPosition()));  
	}
	
}
