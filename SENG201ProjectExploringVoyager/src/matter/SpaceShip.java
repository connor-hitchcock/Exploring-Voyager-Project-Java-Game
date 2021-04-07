package matter;

import java.util.*;
import javax.swing.JOptionPane;
import allitems.*;
import exceptions.*;
import main.GameEnvironment;
import main.RandomEvents;

/**
 * 
 * This class implements the space ship object that handles all the interactions with said space ship,
 * the crew member's on board and the sounding environments, such as landing on a planet or traversing
 * through the galaxy
 *
 */
public class SpaceShip extends Entity {
	private ArrayList<CrewMember> crewList = new ArrayList<CrewMember>();
	private int currency;
	private int maxShieldCapacity;
	private int shieldCapacity;
	private int maxFuelCapacity;
	private int fuelCapacity;
	private int starPieceInventoryLimit;
	private StarPiece[] starPieceInventory;
	private Location[][] plane;
	
	/**
	 * The constructor for the space ship
	 * 
	 * @param tempName the name set to the space ship.
	 * @param tempMaxShield the maximum shield capacity the space ship can have.
	 * @param tempMaxFuel the maximum fuel capacity the space ship cane have.
	 * @param tempMaxHealth the health or space ship hull health is at its maximum.
	 * @param tempItemInventoryLimit the maximum number of slots that the space ship's item inventory can hold 
	 * @param tempStarPieceInventoryLimit the maximum number of slots in the space ship's inventory that can 
	 * only carry star pieces. Determined by how many star pieces in the game are generated.
	 */
	public SpaceShip(String tempName, int tempMaxShield, int tempMaxFuel, int tempMaxHealth, int tempItemInventoryLimit, int tempStarPieceInventoryLimit) {
		super(tempMaxHealth, tempItemInventoryLimit, tempName);
		maxShieldCapacity = tempMaxShield;
		shieldCapacity = tempMaxShield;
		maxFuelCapacity = tempMaxFuel;
		fuelCapacity = tempMaxFuel;
		starPieceInventoryLimit = tempStarPieceInventoryLimit;
		starPieceInventory = new StarPiece[tempStarPieceInventoryLimit];
		currency = 500;
	}
	
	/**
	 * Regenerates the space ship's shield by 5% with each passing game hour, and
	 * is updates whenever in game time is passed.
	 * 
	 * @param hoursPassed The in game hours passed.
	 */
	public void regenerateShields(int hoursPassed) {
		int regenAmount = (int) Math.round(getMaxShieldCapacity() * 0.05 * hoursPassed);
		CrewMember talkingCrewMember = selectCrewMemberToTalk();
		updateShieldCapacity(regenAmount);
	}
	
	/**
	 * Updates the space ship's current shield capacity, and if the maximum shield
	 * capacity is surpassed, the current shield capacity is set at its maximum.
	 * 
	 * @param shieldChange the change in the space ship's shield capacity.
	 */
	public void updateShieldCapacity(int shieldChange) {
		shieldCapacity += shieldChange;
		if(shieldCapacity >= maxShieldCapacity) {
			shieldCapacity = maxShieldCapacity;
		}	
	}

	/**
	 * Damages the space ship's shield capacity. This function is different over the
	 * update one as when the damage is more the space ship's remaining shield capacity,
	 * the function will use the excess damage to decrease the space ship's current health.
	 * 
	 * @param damage the damage that is done the space ship.
	 */
	public void damageShieldCapacity(int damage) {
		if(damage > shieldCapacity) {
			int excessDamage = damage - shieldCapacity;
			updateShieldCapacity(0);
			updateCurrentHealth(-1 * excessDamage);
		} else {
			updateShieldCapacity(-1 * damage);
		}
	} 

	/**
	 * Finds the crew member aboard the space ship with the highest charisma level.
	 * 
	 * @return returns the crew member with the highest charisma level. 
	 */
	public CrewMember findHighestCharismaCharacter() {
		CrewMember highestCharisma = getCrewList().get(0);
		for (CrewMember member: getCrewList()) {
			if (highestCharisma.getCharismaLevel() < member.getCharismaLevel()) {
				highestCharisma = member;
			}
		}
		return highestCharisma;
	}

	/**
	 * Decreases the space ship's fuel capacity by a given amount. And if it does
	 * not have enough fuel, then it returns false.
	 * 
	 * @param fuelUsed the amount the space ship's fuel capacity decreases by.
	 * @return returns true if the fuel was successfully used, or else false
	 * if there is not enough fuel left.
	 */
	public boolean useFuel(int fuelUsed)  { //false if not enough fuel, true if fuel used successfully 
		boolean used = false;
		if((fuelCapacity - fuelUsed) >= 0) {
			fuelCapacity -= fuelUsed;
			used = true;
		}
		return used;
	}
	
	/**
	 * All the crew members aboard the space ship rest and regain some stamina.
	 * 
	 * @param habitability the habitability of the planet affects how much the crew members stamina will increase by.
	 * @param events all the random events that could occur whilst the crew are resting
	 * @return Returns all the updated crew information such as the increased stamina from resting.
	 * @throws RandomEventException if a random event occurs whilst the crew members are resting, then said
	 * exception is called. 
	 * @throws DeadCrewException if a crew member dies aboard the ship whilst resting, then said exception is called.
	 * @throws GameOverException if a parameter that would cause a game over is called, such as out of time or all
	 * crews members die, then said exception is called.
	 * @throws TakenDamageException if a crew member takes damage whilst resting, then said exception is called.
	 */
	public String restAllMembers( int habitability, RandomEvents events) throws RandomEventException, DeadCrewException, GameOverException, TakenDamageException {
		String restInformation = "<html>";
		for (CrewMember crew : getCrewList()) {
				restInformation += (crew.rest(habitability)) + "<br>";
				try {
				events.spacePlague(crew, getCrewList());
				} catch(RandomEventException e) {
				JOptionPane.showMessageDialog(null, e);
				}
			}
		restInformation += "</html>";
		GameEnvironment.addHours(this, 4);
		return restInformation;
	}
	
	public String restAllMembers(RandomEvents events) throws RandomEventException, DeadCrewException, GameOverException, TakenDamageException {
		String restInformation = "<html>";
		for (CrewMember crew : getCrewList()) {
				restInformation += (crew.rest()) + "<br>";
				events.spacePlague(crew, getCrewList());
			}
		restInformation += "</html>";
		GameEnvironment.addHours(this, 4);
		return restInformation;
	}
	
	/**
	 * Checks if your space ship's current location has a planet at it
	 * 
	 * @return returns true if your spaceship is at a planet
	 */
	public boolean checkLocation() {
		boolean onPlanet = false;
		if (getPlane()[getCoordinateX()][getCoordinateY()] instanceof Planet) {
			onPlanet = true;
		}
		return onPlanet;
	}
	
	/**
	 * Updates the space ship's fuel capacity and if the updated current fuel capacity
	 * is above the maximum fuel capacity, it sets it at the space ship's maximum fuel
	 * capacity.
	 * 
	 * @param fuelChange the change in the space ship's current fuel capacity.
	 */
	public void updateFuelCapacity(int fuelChange) {
		fuelCapacity += fuelChange;
		if(fuelCapacity > maxFuelCapacity) {
			fuelCapacity = maxFuelCapacity;
		} else if(fuelCapacity < 0) {
			fuelCapacity = 0;
		}
	}
	
	/**
	 * Checks if the given positions in the given list are within the ingame's 2x2 star map grid.
	 * 
	 * @param posChangeX a list of X coordinate changes from the current position.
	 * @param posChangeY a list the Y coordinate changes from the current position.
	 * @return list of booleans that determine whether said position is within the star map.
	 */
	public boolean[] getPositions(int[] posChangeX, int[] posChangeY ) {
		boolean[] moveTo = new boolean[8];
		for(int i=0; i<8; i++) {
			moveTo[i] = checkBounds((getCoordinateX() + posChangeX[i]), (getCoordinateY() + posChangeY[i]));
		}
		return moveTo;
	}
	
	/**
	 * Checks if the given list of moves are valid (inside the star map).
	 * 
	 * @param moveTo a list of potential valid moves.
	 * @return all the list of validMoves.
	 */
	public int[] calculateValidMoves(boolean[] moveTo) {
		int[] validMoves = {0,0,0,0,0,0,0,0};
		for(int i=0; i<8; i++) {
			if(moveTo[i]) {
				validMoves[i] = 1;
			}
		}
		return validMoves;
	}
	
	/**
	 * Adds the traverse information to the position string, including if the move is
	 * within the star map.
	 * 
	 * @param moveTo list of valid moves.
	 * @param posChangeX list of changes in the X direction from the current position.
	 * @param posChangeY list of changes in the X direction from the current position.
	 * @return 
	 */
	public String[] calculatePosStrings( boolean[] moveTo,int[] posChangeX, int[] posChangeY ) {
		String[] posStrings = new String[8];
		for(int i=0; i<8; i++) {
			if(moveTo[i]) {
				String cordsString = (getCoordinateX() + posChangeX[i]) + ", " + (getCoordinateY() + posChangeY[i]);
				posStrings[i] = "Coord: (" + cordsString + "), Loc: " + plane[getCoordinateX()+ posChangeX[i]][getCoordinateY() + posChangeY[i]];
			} else {
				posStrings[i] = "Beyond Space-Time";
			}
		}
		return posStrings;
	}
	
	
	/**
	 * Updates the position strings with information about the location and if the
	 * said move is in or outside the star map.
	 * 
	 * @return the position strings information.
	 */
	public String[] calculateTraverseData() {
		int[] posChangeX = {-1, 0, 1, -1, 1, -1, 0, 1};
		int[] posChangeY = {1, 1, 1, 0, 0, -1, -1, -1};
		boolean[] moveTo = getPositions(posChangeX, posChangeY);
		String[] posStrings = calculatePosStrings(moveTo, posChangeX, posChangeY);
		return posStrings;
	}
	
	/**
	 * Checks if the user has selected too many or not enough crew members
	 * to go aboard the space ship. If it is too many of few, returns said
	 * exception.
	 * 
	 * @param crewNeeded the number of crew members needed to be selected.
	 * @param crewList the current crew members aboard the space ship.
	 * @return returns true if there are an appropriate amount of crew members selected.
	 * @throws IncorrectCrewNumberException
	 */
	public boolean checkValidCrew(int crewNeeded, ArrayList<CrewMember> crewList) throws IncorrectCrewNumberException { 
		boolean valid = true;
		if (crewList.size() > crewNeeded) {
			throw new IncorrectCrewNumberException("Too many crew were selected.");
		} else  if (crewList.size() < crewNeeded) {
			throw new IncorrectCrewNumberException("Too little crew were selected.");
		}
		return valid;
	}
	
	/**
	 * The main function that allows the player space ship to move around the star map.
	 * 
	 * @param game the whole game and all its associated data.
	 * @param position the space ship's current position.
	 * @param diagonal if the position selected is diagonal of the space ship's current position.
	 * @param pilots the two crew member's selected to pilot the space ship to allow it traverse.
	 * @param events the random events that could happen.
	 * @throws InvalidMoveException if the use selects a position that is outside the star map, then throws said exception.
	 * @throws DeadCrewException if a crew member dies whilst traversing, then throws said exception.
	 * @throws InsufficientStaminaException if a crew member has insufficient stamina to pilot and traverse,
	 * then throws said exception.
	 * @throws InsufficientFuelException if the space ship has insufficient fuel to move to the selected position,
	 * the throws said exception.
	 * @throws RandomEventException If a random event happens whilst traversing, then said exception is thrown.
	 * @throws GameOverException Whilst traversing if a parameter is meet that would cause a game over,
	 * then said exception is thrown.
	 * @throws TakenDamageException If the space ship takes damage whilst traversing then said exception is thrown.
	 */
	public void traverse(GameEnvironment game, int position, boolean diagonal, ArrayList<CrewMember> pilots, RandomEvents events) throws InvalidMoveException, DeadCrewException, InsufficientStaminaException, InsufficientFuelException, RandomEventException, GameOverException, TakenDamageException {
		int[] posChangeX = {-1, 0, 1, -1, 1, -1, 0, 1};
		int[] posChangeY = {1, 1, 1, 0, 0, -1, -1, -1};
		boolean[] moveTo = getPositions(posChangeX, posChangeY);
		int[] validMoves = calculateValidMoves(moveTo);
		
		int fuelUsed = 500;
		if (validMoves[position] == 1) {
			if (diagonal == true) {
				fuelUsed = 1000;
			}
			if(useFuel(fuelUsed)) {
				setCoordinates((getCoordinateX() + posChangeX[(position)]), (getCoordinateY() + posChangeY[(position)]));
				GameEnvironment.addHours(this, 4);
				for (CrewMember pilot: pilots) {
					pilot.checkStamina(10);
				}
				pilots.get(0).updateCurrentStamina(-10);
				pilots.get(1).updateCurrentStamina(-10);
				GameEnvironment.updateScore(250);
				if (getPlane()[getCoordinateX()][getCoordinateY()].getCurrentAsteroidBelt() == null) {
					events.doRandomEvent(this, "traversing");
				} else {
					events.doRandomEvent(this, "asteroidBelt");
				}
			} else {
				throw new InsufficientFuelException("Not enough fuel!");
			}
			
		} else {
			throw new InvalidMoveException("Cannot travel there!");
		}
		
	}
	
	/**
	 * Checks if the given X and Y index are with in the star map.
	 * 
	 * @param indexX the given X coordinate.
	 * @param indexY the given Y coordinate.
	 * @return returns true, if the indices are with in the star map.
	 */
	public boolean checkBounds(int indexX, int indexY) {
		boolean inBoundsX = ((indexX >= 0) && (indexX < plane.length));
		boolean inBoundsY = ((indexY >= 0) && (indexY < plane[0].length));
		boolean inBound = false;
		if(inBoundsX && inBoundsY) {
			inBound = true;
		}
		return inBound;
	}
	
	/**
	 * Selects a random crew member to talk.
	 * 
	 * @return the crew member selected to talk.
	 */
	public CrewMember selectCrewMemberToTalk() {
	    ArrayList<CrewMember> crewList = getCrewList();
		Random personSelector = new Random();
		int crewIndex = personSelector.nextInt(crewList.size());
		return crewList.get(crewIndex);
	}
	
	/**
	 * Checks if the given crew members have enough stamina to do said task.
	 * 
	 * @param staminaRequirement the required stamina the crew members to do said task.
	 * @param crewNeeded the amount of crew members needed to do said task.
	 * @return returns true if there is enough crew members and said stamina to do said task.
	 */
	public boolean checkIfEnoughWorkers(int staminaRequirement, int crewNeeded) {
		boolean enough = false;
		int counter = 0;
		for (CrewMember crew : crewList) {
			if (crew.getCurrentStamina() >= staminaRequirement) {
				counter += 1;
			}
		}
		if (counter >= crewNeeded) {
			enough = true;
		}
		return enough;
	}
 	
	/**
	 * Gets all the items in all the crew members inventories and the space ships inventory.
	 * 
	 * @return all the items in all the inventories.
	 */
	public Item[] getAllItems() {
		// index 0-3, represents crew members items, index 4-12 represent space ship inventory
		Item[] allItems = new Item[13];
		int index = 0;
		for(CrewMember crew : crewList) {
			allItems[index] = crew.getItemInventory()[0];
			index += 1;
		}
		index = 4;
		for(Item shipItem : getItemInventory()) {
			allItems[index] = shipItem;
			index += 1;
		}
		return allItems;
	}

	/**
	 * Checks what item
	 * 
	 * @param tempItem the given item being checked for it type.
	 * @param playerShip the given space ship.
	 * @param usedCrew the crew member who the item will be using the item
	 * @throws MaximumStaminaException passes said exception from further in the code.
	 * @throws MaximumFuelException passes said exception from further in the code.
	 */
	public void useItemType(Item tempItem, SpaceShip playerShip, CrewMember usedCrew) throws MaximumStaminaException, MaximumFuelException {
		String itemType = tempItem.getType();
		switch(itemType) {
		case "Food":
			FoodItem passedItem1 = (FoodItem) tempItem;
			usedCrew.feed(passedItem1, playerShip);
			break;
		case "Fuel Canister":
			FuelCanister passedItem2 = (FuelCanister) tempItem;
			usedCrew.refuel(passedItem2, playerShip);
			break;
		case "Crew Item":
			CrewItem passedItem3 = (CrewItem) tempItem;
			usedCrew.upgrade(passedItem3, playerShip);
			break;
		}
	}
	
	/**
	 * Same as above, but only takes Medical items as they will be used on a crew member.
	 * 
	 * @param onCrew the crew member that will be healed.
	 * @throws MaximumHealthException passes said exception from further in the code.
	 */
	public void useItemTypeOn(Item tempItem, SpaceShip playerShip, CrewMember usedCrew, CrewMember onCrew) throws MaximumHealthException {
		MedicalItem theItem = (MedicalItem) tempItem;
		if(theItem.getSpacePlagueCure()) {
			usedCrew.cureSpacePlague(theItem, playerShip, onCrew);
		} else {
			usedCrew.heal(theItem, playerShip, onCrew);
		}
	}
	
	/**
	 * Checks if the damage done is enough to cause a game over.
	 * 
	 * @param damageTaken the damage done.
	 * @throws GameOverException If enough damage is done to make 
	 */
	public void takenDamage(int damageTaken) throws GameOverException {
		if (checkHealth() == true) {
			GameEnvironment.updateScore(-50);
		} else {
				throw new GameOverException("Game Over, your ship's HP is 0!");
		}
	}
	
	public CrewMember addItemToCrewMember(Item itemToBeAdded) {
		CrewMember addedToCrew = null;
		ArrayList<CrewMember> crewList = getCrewList();
		for (CrewMember crew : crewList) {
			if (crew.getItemInventory()[0] == null && addedToCrew == null) {
				crew.setItem(itemToBeAdded);
				addedToCrew = crew;
			}
		}
		return addedToCrew;
	}
	
	public void removeItem(Item itemToBeRemoved) {
		boolean removed = false;
		int shipCounter = 0;
		Item[] tempInventory = getItemInventory();
		while (removed == false) {
			if (shipCounter < tempInventory.length) {
				if (tempInventory[shipCounter] == itemToBeRemoved) {
					if (itemToBeRemoved.getCount() > 1) {
						itemToBeRemoved.setCount(itemToBeRemoved.getCount() - 1);
						removed = true;
					} else {
						tempInventory[shipCounter] = null;
						removed = true;
					}
				}
				shipCounter += 1;
			} else {
				boolean found = false;
				for (CrewMember crew : getCrewList()) {
					for (Item item :crew.getItemInventory()) {
						if (item == itemToBeRemoved && found == false) {
							found = true;
							if (itemToBeRemoved.getCount() > 1) {
								itemToBeRemoved.setCount(itemToBeRemoved.getCount() - 1);
								Item[] newCrewInventory = {itemToBeRemoved};
								crew.setItemInventory(newCrewInventory);
							} else {
							Item[] tempCrewInventory = {null};
							crew.setItemInventory(tempCrewInventory);
						}
					}
				}	
			}
			removed = true;
		}
		shipCounter += 1;
		if (removed == true) {
			setItemInventory(tempInventory);
		}
		}
	}

	public void updateCurrency(int currencyChange) {
		currency += currencyChange;
		GameEnvironment.updateScore(10 * currencyChange);
	}

	
	public void addCrewMember(CrewMember tempCrewMember) {
		crewList.add(tempCrewMember);
	}
	
	public void removeCrewMember(ArrayList<Integer> indices) throws DeadCrewException, GameOverException {
		ArrayList<CrewMember> tempCrewList = getCrewList();
		Collections.sort(indices, Collections.reverseOrder());  
		String deathText = "<html>";
		for (int index : indices) {
			tempCrewList = getCrewList();
			CrewMember tempCrewMember = tempCrewList.remove(index);
			deathText += (tempCrewMember.getName() + "'s lifeless body gets sent out of the airlock...<br>");
			deathText += (tempCrewMember.getName() + " was removed from the party.<br>");
			GameEnvironment.updateScore(-500);
		}
		deathText += "</html>";
		if (indices.size() >= 1) {
			if (tempCrewList.size() >= 2) {
				setCrewList(tempCrewList);
				throw new DeadCrewException(deathText);
			} else {
				throw new GameOverException("And you are left alone, unable to pilot your ship...");
			}
		}
	}
	public boolean checkStarPieces() {
		boolean allStarPieces = false;
		if (getInventoryLength(getStarPieceInventory()) == getStarPieceInventory().length) {
			allStarPieces = true;
			GameEnvironment.finishedGame = true;
			GameEnvironment.updateScore(5000);
		}
		return allStarPieces;
	}
	
	public void addToStarPieceInventory(StarPiece addedStarPiece) {
		int nextEmptySlot = getInventoryLength(getStarPieceInventory());
		starPieceInventory[nextEmptySlot] = addedStarPiece;

	}
	
	public void setCrewList(ArrayList<CrewMember> tempCrewList) {
		crewList = tempCrewList;
	}
	public void setPlane(Location[][] tempPlane) {
		plane = tempPlane;
	}
	public int getStarPieceInventoryLimit() {
		return starPieceInventoryLimit;
	}
	public StarPiece[] getStarPieceInventory() {
		return starPieceInventory;
	}
	public int getMaxShieldCapacity() {
		return maxShieldCapacity;
	}
	public int getShieldCapacity() {
		return shieldCapacity;
	}
	public int getMaxFuelCapacity() {
		return maxFuelCapacity;
	}
	public int getFuelCapacity() {
		return fuelCapacity;
	}
	public int getCurrency() {
		return currency;
	}
	public ArrayList<CrewMember> getCrewList() {
		return crewList;
	}
	public void getShipInfo() {
		this.getCurrentHealth();
	}
	public Location[][] getPlane() {
		return plane;
	}
	
	public String toString() {
		String out = "";
		out = "SpaceShip is at position: " + getCoordinateX() + ", " + getCoordinateY();
		out += " | " + getPlane();
		return out;
	}
}
