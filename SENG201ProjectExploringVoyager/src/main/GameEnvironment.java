package main;

import java.util.*;
import allitems.*;
import exceptions.*;
import matter.*;

public class GameEnvironment {
	private int totalDays;
	private static int totalHours;
	private static int hoursTaken;
	private static int score;
	private static RandomEvents events;
	private int numOfStarPieces;
	private static Location[][] starMap;
	private static SpaceShip playerShip;
	public static boolean finishedGame = false;
	public static boolean badEnd = false;
	
	public GameEnvironment(int tempDayLimit, Location[][] tempStarMap) {
		totalDays = tempDayLimit;
		totalHours = tempDayLimit * 24;
		hoursTaken = 0;
		score = 0;
		starMap = tempStarMap;
		events = new RandomEvents();
	}
	
	public GameEnvironment(int tempDayLimit) {
		totalDays = tempDayLimit;
		totalHours = tempDayLimit * 24;
		hoursTaken = 0;
		score = 0;
		events = new RandomEvents();
	}
	
	public GameEnvironment() {
		hoursTaken = 0;
		score = 0;
		events = new RandomEvents();
	}
	


	public void generateGame(int days, SpaceShip playerShip, String shipName, ArrayList<CrewMember> crewList) {
		setStarMap(generateStarMap(days));
		setTotalHours(days);
		playerShip.setName(shipName);
		playerShip.setPlane(starMap);
		playerShip.setCrewList(crewList);
		setPlayerShip(playerShip);
		playerShip.setCoordinates(1, 1);
	}

	public Location[][] generateStarMap(int dayLimit) {
		// decided formula, if days
		int[] divArray = { 4, 4, 4, 5,
							6, 7, 8, 6 };
		int[] asteroidArray = {3, 6, 7, 8,
								10, 12, 14, 16};
	    ArrayList<StarPiece> starPieceList = generateStarPieces(numOfStarPieces);
		int numberOfPlanets = Planet.generateNumberOfPlanets(dayLimit);
		int numberOfSquares = generateNumberOfSquares(numberOfPlanets);
		int numberOfAsteroidBelts = asteroidArray[dayLimit - 3];
		int length = divArray[dayLimit - 3];
		int height = numberOfSquares / length;
		Location[][] generatedMap = new Location [length][height];
		for (int i=0; i < length; i++) {
			for (int j=0; j < height; j++) {
				generatedMap[i][j] = new Location(i,j);
			}
		}
		Location[][] updatedMap = generatePlanets(numberOfPlanets, generatedMap, starPieceList); //fix this to take starpieces
	    updatedMap = generateAsteroidBelts(numberOfAsteroidBelts, updatedMap);
		return updatedMap;
	}
	
	public void generateNumberStarPieces(int dayLimit) { //note its static so then we can access it form the GameEnvironment Class.
		int numberOfPieces = (dayLimit * 2) / 3;
		numOfStarPieces = numberOfPieces;
	}
	
	public int generateNumberOfSquares(int numberOfPlanets) {
		return numberOfPlanets * 4;
	}
	
	
	public ArrayList<StarPiece> generateStarPieces(int numberOfStarPieces) { 
		ArrayList<StarPiece> starPieceList = new ArrayList<StarPiece>();
		switch(numberOfStarPieces) {
			case 6: StarPiece starPiece6 = new StarPiece("Star Rudder", "Piece 6 out of " + numberOfStarPieces + " of your missing ship.");
					starPieceList.add(starPiece6);
			case 5:	StarPiece starPiece5 = new StarPiece("Star Orbiter", "Piece 5 out of " + numberOfStarPieces + " of your missing ship.");
					starPieceList.add(starPiece5);
			case 4:	StarPiece starPiece4 = new StarPiece("Star Gear", "Piece 4 out of " + numberOfStarPieces + " of your missing ship.");
					starPieceList.add(starPiece4);
			case 3:	StarPiece starPiece3 = new StarPiece("Star Fuel", "Piece 3 out of " + numberOfStarPieces + " of your missing ship.");
					starPieceList.add(starPiece3);
			case 2:	StarPiece starPiece2 = new StarPiece("Star Booster", "Piece 2 out of " + numberOfStarPieces + " of your missing ship.");
				    starPieceList.add(starPiece2);
				   	StarPiece starPiece1 = new StarPiece("Star Thruster", "Piece 1 out of " + numberOfStarPieces + " of your missing ship.");
				    starPieceList.add(starPiece1);
				   	break;
		}
		return starPieceList;	
	}
	
	public Location[][] generatePlanets(int numberOfPlanets, Location[][] generatedMap, ArrayList<StarPiece> starPieceList) {
		String[] planetNameArray = {"Alpha-", "Beta-", "Gamma-", "Delta-", "Epsilon-", "Zeta-", "Eta-", "Theta-", "Iota-",
				"Kappa-", "Lambda-", "Mu-", "Nu-", "Xi-", "Omega-", "Psi-"
		};
		Random planetSelector = new Random();
		Random numberSelector = new Random();
		boolean[] planetAddedArray = new boolean[16];
		Arrays.fill(planetAddedArray, false);
		Location[][] mapWithPlanets = generatedMap; //**************************PLEASE ENSURE IT CREATES A NEW LIST
		int numAddedPlanets = 0;
		while (numAddedPlanets < numberOfPlanets) {
			int nameIndex = planetSelector.nextInt(16);
			if (planetAddedArray[nameIndex] == false) {
				StarPiece addedStarPiece = null;
				if (starPieceList.size() > 0) {
					addedStarPiece = starPieceList.remove(0);
				}
				int planetIndex = numberSelector.nextInt(899) + 100;
				String planetName = planetNameArray[nameIndex] + Integer.toString(planetIndex);
				mapWithPlanets = placePlanet(planetName, mapWithPlanets, addedStarPiece);
				planetAddedArray[nameIndex] = true;
				numAddedPlanets += 1;
			}
		}
		return mapWithPlanets;
	}
	
	public Location[][] placePlanet(String planetName, Location[][] mapWithPlanets, StarPiece addedStarPiece) {
		boolean planetPlaced = false;
		while (planetPlaced == false) {
			Random planetRandomiser = new Random();
			int cordX = planetRandomiser.nextInt(mapWithPlanets.length);
			int cordY = planetRandomiser.nextInt(mapWithPlanets[0].length);
			//Create Error Exception when all grid spaces are full 
			if (mapWithPlanets[cordX][cordY].checkEmpty()) { 
				Planet newPlanet = null;
				if (addedStarPiece != null) {
					newPlanet = new Planet(planetRandomiser.nextInt(4), planetRandomiser.nextInt(4), cordX, cordY, planetName, addedStarPiece);
				} else {
					newPlanet = new Planet(planetRandomiser.nextInt(4), planetRandomiser.nextInt(4), cordX, cordY, planetName);
				}
				mapWithPlanets[cordX][cordY] = newPlanet;
				mapWithPlanets[cordX][cordY].setCurrentPlanet(newPlanet);
				
				planetPlaced = true;
			}
		}
		return mapWithPlanets;
	}
	
	public Location[][] generateAsteroidBelts(int numberOfAsteroids, Location[][] starMap) {
		Location[][] updatedStarMap = starMap;
		for (int i=0; i < numberOfAsteroids; i++) {
			boolean asteroidPlaced = false;
			while (asteroidPlaced == false) {
				Random asteroidRandomiser = new Random();
				int cordX = asteroidRandomiser.nextInt(updatedStarMap.length);
				int cordY = asteroidRandomiser.nextInt(updatedStarMap[0].length);
				if (updatedStarMap[cordX][cordY].checkEmpty()) { 
					AsteroidBelt tempBelt = new AsteroidBelt(cordX, cordY);
					updatedStarMap[cordX][cordY] = tempBelt;
					updatedStarMap[cordX][cordY].setCurrentAsteroidBelt(tempBelt);
					asteroidPlaced = true;
				}
			}
		}
		return updatedStarMap;
	}

	public SpaceShip[] selectSpaceShip() {
		SpaceShip Eagle = new SpaceShip("Eagle", 100, 7500, 200, 9, getNumOfStarPieces());
		SpaceShip Enterprise = new SpaceShip("Enterprise", 300, 4000, 100, 5, getNumOfStarPieces());
		SpaceShip Falcon =  new SpaceShip("Falcon" , 200, 5500, 200, 5, getNumOfStarPieces());
		SpaceShip Express = new SpaceShip("Express" , 200, 10000, 50, 5, getNumOfStarPieces());
		SpaceShip Majesty =  new SpaceShip("Majesty", 0, 7500, 50, 9, getNumOfStarPieces());
		SpaceShip[] shipHangar = {Eagle, Enterprise, Falcon, Express, Majesty};
		return shipHangar;
	}
	
	public ArrayList<CrewMember> generateCrewMembers() {
		ArrayList<CrewMember> selectableMembers = new ArrayList<CrewMember>();
		selectableMembers.add(new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3)); // +4
		selectableMembers.add(new CrewMember("Medic", 200, 100, 1, 1, 1, 3, 3)); // +4
		selectableMembers.add(new CrewMember("Mechanic", 150, 150, 3, 1, 1, 1, 3)); // +3
		selectableMembers.add(new CrewMember("Scavenger", 150, 150, 1, 1, 3, 1, 3)); // +4
		selectableMembers.add(new CrewMember("Specialist", 100, 100, 1, 1, 1, 1, 6)); // +0
		selectableMembers.add(new CrewMember("Extra Specialist", 100, 100, 1, 1, 1, 1, 6)); // +0 
		return selectableMembers;
	}
	
	public boolean checkShipLocation(SpaceShip playerShip) {
		boolean atPlanet = false;
		int shipXCoord = playerShip.getCoordinateX();
		int shipYCoord = playerShip.getCoordinateY();
		if  (getStarMap()[shipXCoord][shipYCoord].getCurrentPlanet() instanceof Planet) {
			atPlanet = true;
		}
		return atPlanet;
	}
	
	public static boolean checkTime() {
		boolean overtime = false;
		if (getHoursTaken() >= getTotalHours()) { 
			overtime = true;
		}
		return overtime;
	}
	
	public static void addHours(SpaceShip playerShip, int tempHours) throws GameOverException, DeadCrewException, TakenDamageException {
		hoursTaken += tempHours; 
		if(checkTime()) {
			throw new GameOverException("Your ship halts, as you realise you weren't able to collect all the pieces in time...");
		} else {
			playerShip.regenerateShields(tempHours);
			CrewMember.spacePlagueDamage(playerShip, tempHours);
		}
	}

	public void setPlayerShip(SpaceShip tempShip) {
		playerShip = tempShip;
	}
	
	public SpaceShip getPlayerShip() {
		return playerShip;
	}
	public void setTotalDays(int tempTotalDays) {
		totalDays = tempTotalDays;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setStarMap(Location[][] tempStarMap) {
		starMap = tempStarMap;
	}
	public void setTotalHours(int days) {
		totalHours = days * 24;
	}
	
	public static int getTotalHours() {
		return totalHours;
	}
	
	public static int getHoursTaken() {
		return hoursTaken;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void updateScore(int tempScoreAdditive) {
		score += tempScoreAdditive;
	}
	
	public int getNumOfStarPieces() {
		return numOfStarPieces;
	}
	
	public RandomEvents getEvents() {
		return events;
	}
	
	public static Location[][] getStarMap() {
		return starMap;
	}
	
}
