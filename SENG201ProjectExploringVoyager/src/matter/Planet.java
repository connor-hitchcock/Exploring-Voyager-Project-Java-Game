package matter;

import java.util.*;
import allitems.StarPiece;

/**
 * 
 * This class deals with the Planet object and all the associated functions.
 *
 */

public class Planet extends Location {
	private int infrastructure;
	private int habitability;
	private PlanetLocation[][] landMap;
	private StarPiece starPieceOnPlanet;
	private int[] exploredPositions = {0,0,0,0};
	
	/**
	 * The constructor for the planet object.
	 * 
	 * @param tempInfrastructure the infrastructure level of the planet, determines the frequency and quality of generated objects on the planet.
	 * @param tempHabitability the habitability level of the planet, determines how well crew members will rest and therefore how much stamina they will regain.
	 * @param tempCoordinateX the X coordinate of the planet in the star map.
	 * @param tempCoordinateY the Y coordinate of the planet in the star map.
	 * @param tempPlanetName the name of the planet.
	 * @param tempStarPiece the star piece item if there is one generated on the planet.
	 */
	public Planet(int tempInfrastructure, int tempHabitability, int tempCoordinateX, int tempCoordinateY, String tempPlanetName, StarPiece tempStarPiece) {
		super(tempCoordinateX, tempCoordinateY, tempPlanetName);
		infrastructure = tempInfrastructure;
		habitability = tempHabitability;
		starPieceOnPlanet = tempStarPiece;
		landMap = generateLandMap();
	}
	
	public Planet(int tempInfrastructure, int tempHabitability, int tempCoordinateX, int tempCoordinateY, String tempPlanetName) {
		super(tempCoordinateX, tempCoordinateY, tempPlanetName);
		infrastructure = tempInfrastructure;
		habitability = tempHabitability;
		landMap = generateLandMap();
	}
	
	public Planet(int tempCoordinateX, int tempCoordinateY, String tempPlanetName) {
		super(tempCoordinateX, tempCoordinateY, tempPlanetName);
		infrastructure = 0;
		habitability = 0;
		landMap = generateLandMap();
	}
	
	/**
	 * Generates the number of planets that will be put into the star map, dependent on the number of days selected.
	 * 
	 * @param dayLimit the number of days selected.
	 * @return the number of planets that will be generated.
	 */
	public static int generateNumberOfPlanets(int dayLimit) { //note its static so then we can access it form the GameEnvironment Class.
		int numberOfPlanets = dayLimit - 1;
		return numberOfPlanets;
	}
	
	/**
	 * Generates the 2x2 grid land map of the planet.
	 * 
	 * @return the generated land map.
	 */
	public PlanetLocation[][] generateLandMap() {
		PlanetLocation[][] tempLandMap = new PlanetLocation [2][2]; // check (should be of length 2 (0,1))
		for (int i=0; i < 2; i++) {
			for (int j=0; j < 2; j++) {
				tempLandMap[i][j] = new PlanetLocation(i, j, "Land"); //add names later
			}
		}
		Random outpostSelector = new Random();
	    int outpostChance = outpostSelector.nextInt(99);
		if ((outpostChance >= 20) && (outpostChance <= 89)) { //70% chance for 1 outpost
			tempLandMap = generateOutpost(tempLandMap);
		} else if ((outpostChance >= 90) && (outpostChance < 100)) { //10% chance of 2 outposts
			tempLandMap = generateOutpost(tempLandMap);
			tempLandMap = generateOutpost(tempLandMap);
		} // 20% for no outposts
		tempLandMap = addLandMarks(tempLandMap);
		return tempLandMap;
	}
	
	/**
	 * Generates and adds Land Marks to the planet's land map.
	 * 
	 * @param landMap the planet's land map.
	 * @return returns an updated land map of the planet with the added Land Marks.
	 */
	public PlanetLocation[][] addLandMarks(PlanetLocation[][] landMap) {
		PlanetLocation[][] tempLandMap = landMap;
		ArrayList<int[]> landMarkLocations = new ArrayList<int[]>();
		for (int i=0; i < 2; i++) {
			for (int j=0; j < 2; j++) {
				if (tempLandMap[i][j].getCurrentOutpost() == null) {
					tempLandMap[i][j].setCurrentLandMark(new LandMark(i,j));
					int[] coordinateArray = {i, j};
					landMarkLocations.add(coordinateArray);
				}
			}
		}
		if (getStarPieceOnPlanet() != null) {
			Random locationChooser = new Random();
			int starPiecePosition = locationChooser.nextInt(landMarkLocations.size()); //error check
			int xCoord = landMarkLocations.get(starPiecePosition)[0];
			int yCoord = landMarkLocations.get(starPiecePosition)[1];
			tempLandMap[xCoord][yCoord].getCurrentLandMark().setCurrentStarPiece(getStarPieceOnPlanet());
		}
		return tempLandMap;
		
	}
	
	/**
	 * Generates and adds Outposts to the planet's land map.
	 * 
	 * @param planetMap the planet's land map.
	 * @return returns an updated land map of the planet with the add Outposts.
	 */
	public PlanetLocation[][] generateOutpost(PlanetLocation[][] planetMap) {
	    String outpostName = "Outpost-";
		String[] stringArray = {"Bash", "Horizon", "Luna", "New Leaf", "Teey",
				"Dwarf", "Galaxy", "Pulsar", "Supernova", "Cosmo",
				"Comet", "Nebula", "Nova", "Orb", "Quasar",
				"Giant", "Newt", "Home", "Haven", "Rest"};
	    Random outpostRandomiser = new Random(); 
		int nameIndex = outpostRandomiser.nextInt(20);
		boolean added = false;
		int positionX = 0;
		int positionY = 0;
		int habitabilityMod = 0;///SHOULD FIX THIS
		int infrastructureMod = 0;
		while (added == false) {
			positionX = outpostRandomiser.nextInt(2);
			positionY = outpostRandomiser.nextInt(2);
			if (planetMap[positionX][positionY].getName() == "Land") {
				habitabilityMod = getHabitability() + outpostRandomiser.nextInt(3) - 1;  ///SHOULD FIX THIS
				infrastructureMod = getInfrastructure() + outpostRandomiser.nextInt(3) - 1;
				Outpost tempOutpostPos = new Outpost(positionX, positionY, habitabilityMod, infrastructureMod); //done such that no static variables need to be used. //may need to delete the object later
				outpostName += Integer.toString(tempOutpostPos.returnPosition()) + "-" + stringArray[nameIndex];
				added = true;
			}
		}
		Outpost tempOutpost = new Outpost(positionX, positionY, habitabilityMod, infrastructureMod, outpostName); 
		planetMap[positionX][positionY] = tempOutpost;// needs error handling
		planetMap[positionX][positionY].setCurrentOutpost(tempOutpost);
		return planetMap;
	} 
	
	public int getInfrastructure() {
		return infrastructure;
	}
	public int getHabitability() {
		return habitability;
	}
	public int[] getExploredPositions() {
		return exploredPositions;
	}
	public StarPiece getStarPieceOnPlanet() {
		return starPieceOnPlanet;
	}
	public void setStarPieceOnPlanet(StarPiece tempStarPiece) {
		starPieceOnPlanet = tempStarPiece;
	}
	public PlanetLocation[][] getLandMap() {
		return landMap;
	}
	public void setExploredPosition(int explored) {
		exploredPositions[explored] = 1;
	}
}
