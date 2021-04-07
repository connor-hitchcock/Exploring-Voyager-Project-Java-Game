package matter;

import java.util.Random;
import allitems.*;

/**
 * 
 * This class implements the Land Marks that will be placed on the planets,
 * and deals with all the interacts with said land mark, such as giving
 * you in game currency.
 * 
 */

public class LandMark extends PlanetLocation{
	private StarPiece currentStarPiece;
	
	/**
	 * The constructor for the Outpost.
	 * 
	 * @param tempCoordinateX the X coordinate of the Land Mark on the planet's map grid.
	 * @param tempCoordinateY the Y coordinate of the Land Mark on the planet's map grid.
	 */
	public LandMark(int tempCoordinateX, int tempCoordinateY) {
		super(tempCoordinateX, tempCoordinateY, selectName());
	}
	
	/**
	 * Randomly generates a name for the Land Mark and returns it.
	 * 
	 * @return the randomly generated name of the Land Mark.
	 */
	public static String selectName() {
		String landMarkName = "";
		String[] adjectives = {"Lost ", "Cold ", "Gracious ", "Gigantic ", "Pleasant ", "Calm ",  "Peaceful ", "Rugged ", "Broken "};
		String[] landMarkNames = {"Rock", "Falls", "Lake", "Canyon", "Plains", "Desert", "Plateau", "Forest", "Grassland",
								"Cliff", "Tundra", "Savanna", "Mountain", "Crag", "Bluff", "Overhang", "Wetlands" };
		Random nameSelector = new Random();
		landMarkName += adjectives[nameSelector.nextInt(8)];
		landMarkName += landMarkNames[nameSelector.nextInt(16)];
		return landMarkName;
	}
	
	/**
	 * Randomly generates an item to give to the crew member and returns said item.
	 * 
	 * @return the randomly generated item.
	 */
	public Item itemChooser() { 
		Item item = null;
		Random rewardChooser = new Random();
		int itemInt = rewardChooser.nextInt(4);
		switch(itemInt) {
			case 0: item = generateFoodItem();
					break;
			case 1: item = generateFuelCanister();
					break;
			case 2: item = generateMedicalItem();
					break;
			case 3: item = generateCrewItem();
			break;
		}
		return item;
	}
	
	public StarPiece getCurrentStarPiece() {
		return currentStarPiece;
	}
	public void setCurrentStarPiece(StarPiece tempStarPiece) {
		currentStarPiece = tempStarPiece;
		
	}
	public void removeCurrentStarPiece() {
		currentStarPiece = null;
	}
}
