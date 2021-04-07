package matter;

import java.util.*;
import allitems.*;
import exceptions.*;
import main.GameEnvironment;
import  java.lang.Math.*;

/**
 * 
 * This class implements the crew members that will put on the spaceship and interact with there
 * environment. This class deals with every action and statistic associated with them, such
 * as exploring a planet and there stamina levels.
 * 
 */

public class CrewMember extends Entity { 
	
	private int repairLevel;
	private int scavengingLevel;
	private int maxStamina;
	private int currentStamina;
	private int charismaLevel;
	private int healerLevel;
	private boolean hasSpacePlague;
	private int daysWithSpacePlague;
	private int unallocatedStatPoints;
	private Item heldItem;
	
	/**
	 * The constructor for the crew member class.
	 * 
	 * @param tempName the name of the crew member.
	 * @param tempMaxHealth the maximum possible health of the crew member.
	 * @param tempMaxStamina the maximum possible stamina of the crew member.
	 * @param tempRepairLevel the repair level of the crew member.
	 * @param tempCharismaLevel the charisma level of the crew member.
	 * @param tempScavangingLevel the scavenging level of the crew member.
	 * @param tempHealerLevel the healer or healing level of the crew member.
	 * @param tempUnallocatedStats the stat points that have yet to be used.
	 * to increase the maximum level of a multitude of the crew members stats.
	 */
	public CrewMember(String tempName, int tempMaxHealth, int tempMaxStamina, int tempRepairLevel, int tempCharismaLevel, int tempScavangingLevel, int tempHealerLevel, int tempUnallocatedStats) {
		super(tempMaxHealth, 1, tempName);
		maxStamina = tempMaxStamina;
		currentStamina = tempMaxStamina;
		repairLevel = tempRepairLevel;
		scavengingLevel = tempScavangingLevel;
		charismaLevel = tempCharismaLevel;
		healerLevel = tempHealerLevel;
		hasSpacePlague = false;
		daysWithSpacePlague = 0;
		unallocatedStatPoints = tempUnallocatedStats;
	}
	
	/**
	 * Checks if the crew member has enough stamina to do a particular task.
	 * 
	 * @param staminaRequirement the stamina required to do said task
	 * @throws InsufficientStaminaException if the crew member does not
	 * have enough stamina to do the task, then said exception is thrown.
	 */
	public void checkStamina(int staminaRequirement) throws InsufficientStaminaException {
		if (getCurrentStamina() < staminaRequirement) {
			throw new InsufficientStaminaException(getName() + " doesn't have enough stamina to perform this task!");
		}
	}
	
	/**
	 * Allows the crew member to interact and explore a given planets grid.
	 * 
	 * @param cordX the X coordinate on the planet.
	 * @param cordY the Y coordinate on the planet.
	 * @param currentPlanet the current planet the space ship is on.
	 * @param playerShip the space ship.
	 * @return returns a explored planet location if a crew member explores it.
	 * @throws InsufficientStaminaException if the crew member does not have
	 * enough stamina to explore, then said exception is thrown
	 * @throws GameOverException if a parameter is meet that would cause a
	 * game over, then said exception is thrown.
	 * @throws DeadCrewException if a crew member dies while exploring,
	 * then said exception is thrown.
	 * @throws TakenDamageException if a crew member takes damage, then
	 * said exception is thrown.
	 */
	public PlanetLocation explore(int cordX, int cordY, Planet currentPlanet, SpaceShip playerShip) throws InsufficientStaminaException, GameOverException, DeadCrewException, TakenDamageException {
		PlanetLocation exploreType = null;
		PlanetLocation[][] landMap = currentPlanet.getLandMap();
		int[] exploredPositions = currentPlanet.getExploredPositions();
		int position = (2 * cordX) + cordY;
		if(exploredPositions[position] == 1) {
			if (landMap[cordX][cordY].getCurrentOutpost() != null) {
				exploreType = landMap[cordX][cordY]; //change
			}
		} else {
			GameEnvironment.addHours(playerShip, 2);
			checkStamina(30);
			updateCurrentStamina(-30);
			currentPlanet.setExploredPosition(position);
			GameEnvironment.updateScore(500);
			if (landMap[cordX][cordY].getCurrentLandMark() instanceof LandMark) {
				exploreType = landMap[cordX][cordY];
			}
		}
		return exploreType;
	}

	/**
	 * Searches the current land mark and gives the crew member randomly generated rewards.
	 * 
	 * @param currentLandMark the current Land Mark the crew member is at
	 * @param playerShip the space ship
	 * @return a string that contains a message of what the crew member has found.
	 */
	public String[] searchLandMark(LandMark currentLandMark, SpaceShip playerShip) { //finish
		Item rewardItem = null;
		String[] rewardString = new String[2];
		rewardString[1] = "other";
		if (currentLandMark.getCurrentStarPiece() != null) {
			StarPiece rewardPiece = currentLandMark.getCurrentStarPiece();
			currentLandMark.removeCurrentStarPiece();
			rewardString[0] = ("***" + rewardPiece.getName() + " was found!!!!***"); //ADD TO STARPIECE INV 
			playerShip.addToStarPieceInventory(rewardPiece);
			rewardString[1] = "StarPiece";
		} else  {
			Random rewardChooser = new Random();
			int rewardID = rewardChooser.nextInt(100);
			if (rewardID <= (79 - (getScavengingLevel() * 2))) {
		    	int additionalMoney = rewardChooser.nextInt(151 + (getScavengingLevel() * 75)) + 150;
		    	rewardString[0] = (additionalMoney + " space credits was found!");
				playerShip.updateCurrency(additionalMoney); 
			} else if (rewardID <= 94) {
				rewardItem = currentLandMark.itemChooser();
				rewardString[0] = ("A " + rewardItem.getName() + " was found!");
			} else {
				if (getHasSpacePlague() == false) {
					rewardString[0] = (getName() + " doesn't look too good?.. They're showing symptoms of the Space Plague!");
					setHasSpacePlague(true);
				} else {
					rewardString[0] = (getName() + " found some green goo, which they threw away.");
				}
			}
		}
		if (rewardItem != null) {
			//add to inventory
			boolean added = playerShip.addItem(rewardItem);
			if (added == false) {
				CrewMember crewAddedTo = playerShip.addItemToCrewMember(rewardItem); //TODO
			}
	}
		return rewardString;
	}
	
	/**
	 * Checks if the space ship is already at its maximum health.
	 * 
	 * @param playerShip the space ship
	 * @throws ShipMaximumHealthException if the space ship is
	 * at its maximum health, then said exception is thrown.
	 */
	public void checkRepair(SpaceShip playerShip) throws ShipMaximumHealthException {
		if(playerShip.getCurrentHealth() == playerShip.getMaxHealth()) {
			throw new ShipMaximumHealthException(playerShip.getName() + " is at maximum hull integrity!");
		}
	}
	
	/**
	 * Uses a crew member to repair the space ship and increase its current health.
	 * 
	 * @param playerShip the space ship.
	 * @return a message string that contains how much the ship has been repaired.
	 * @throws ShipMaximumHealthException if the space ship is already at 
	 * maximum health, then said exception is thrown.
	 * @throws InsufficientStaminaException if the crew member has insufficient
	 * stamina to repair the ship, then said exception is thrown.
	 */
	public String repair(SpaceShip playerShip) throws ShipMaximumHealthException, InsufficientStaminaException {
		checkRepair(playerShip);
		checkStamina(30);
		int repairAmount = (int) Math.round(10 * (2 + repairLevel));
		playerShip.updateCurrentHealth(repairAmount);
		updateCurrentStamina(-30);
		GameEnvironment.updateScore(500);
		return (getName() + ": Successfully repaired the ship by " + repairAmount + " captain, the ship has " + playerShip.getCurrentHealth() + "/" + getMaxHealth() + " health.");
		
	}

	/**
	 * Checks if the crew member is already at there maximum stamina
	 * 
	 * @throws MaximumStaminaException if the crew member is at
	 * there maximum stamina, then said exception is thrown.
	 */
	public void checkMaxStamina() throws MaximumStaminaException {
		if(getCurrentStamina() == getMaxStamina()) {
			throw new MaximumStaminaException(getName() + " is already at maximum stamina!");
		}
	}
	
	/**
	 * A crew members consumes a Food Item and regenerates some of the stamina
	 * 
	 * @param foodItem the Food item the crew member consumes.
	 * @param playerShip the space ship.
	 * @throws MaximumStaminaException if the crew member is already have
	 * maximum stamina, then said exception is thrown.
	 */
	public void feed(FoodItem foodItem, SpaceShip playerShip) throws MaximumStaminaException {
		checkMaxStamina();
		updateCurrentStamina(foodItem.getFillingFactor());
		playerShip.removeItem(foodItem);
		GameEnvironment.updateScore(50);
	}
	
	/**
	 * Checks if the crew member is already at there maximum health
	 * 
	 * @throws MaximumHealthException if the crew member is already
	 * at there maximum health, then said exception is thrown.
	 */
	public void checkMaxHealth() throws MaximumHealthException {
		if( getCurrentHealth() == getMaxHealth()) {
			throw new MaximumHealthException(getName() + " is already at maximum health!");
		}
	}
	
	/**
	 * A selected crew member uses a Medical Item to heal another selected crew member,
	 * which will increase said crew member's current health.
	 * 
	 * @param healItem the given Medical Item to be used.
	 * @param playerShip the space ship.
	 * @param crewHealing the crew member that will be healed.
	 * @throws MaximumHealthException if the crew member being healed,
	 * is already at there maximum health, then said exception is thrown.
	 */
	public void heal(MedicalItem healItem, SpaceShip playerShip, CrewMember crewHealing) throws MaximumHealthException {
		crewHealing.checkMaxHealth();
		crewHealing.updateCurrentHealth((healItem.getHealingFactor() + (crewHealing.getHealerLevel() * 10)));
		GameEnvironment.updateScore(100);
		playerShip.removeItem(healItem);
	}
	
	/**
	 * A selected crew member uses a Medical Item to cure another selected crew member
	 * space plague.
	 * 
	 * @param spacePlagueCure the Medical Item that can cure space plague.
	 * @param playerShip the space ship.
	 * @param crewHealing the crew member that will have there space
	 * plague cured.
	 */
	public void cureSpacePlague(MedicalItem spacePlagueCure, SpaceShip playerShip, CrewMember crewHealing) {
		crewHealing.setHasSpacePlague(false);
		GameEnvironment.updateScore(500);
		playerShip.removeItem(spacePlagueCure);
	}
	
	/**
	 * Checks if the space ship is at maximum fuel capacity.
	 * 
	 * @param playerShip the space ship.
	 * @throws MaximumFuelException if the space ship is at
	 * maximum fuel capacity, then said exception is thrown.
	 */
	public void checkMaxFuel(SpaceShip playerShip) throws MaximumFuelException {
		if(playerShip.getFuelCapacity() == playerShip.getMaxFuelCapacity()) {
			throw new MaximumFuelException(playerShip.getName() + " is at maximum fuel capacity!");
		}
	}
	
	/**
	 * Increases the space ship's fuel capacity by a said amount using a
	 * Fuel Canister Item.
	 * 
	 * @param fuelItem the Fuel Canister item that will be used.
	 * @param playerShip the space ship.
	 * @throws MaximumFuelException If the space ship is already at
	 * its maximum fuel capacity, then said exception is thrown.
	 */
	public void refuel(FuelCanister fuelItem, SpaceShip playerShip) throws MaximumFuelException {
		checkMaxFuel(playerShip);
		playerShip.updateFuelCapacity(fuelItem.getFuellingFactor());
		playerShip.removeItem(fuelItem);
	}

	/**
	 * Upgrades a particular stat on the spaceship using a Crew Item.
	 * 
	 * @param crewsItem the Crew Item to be used.
	 * @param playerShip the space ship.
	 */
	public void upgrade(CrewItem crewsItem, SpaceShip playerShip) {
		changeStat(crewsItem.getUpgradeType(), true, this); 
		playerShip.removeItem(crewsItem);
	}
	
	/**
	 * All the crew members rest on the planet and their stamina increases
	 * based on the planet's habitability.
	 * 
	 * @param habitability the planet's habitability, which ranges from 0-3.
	 * @return a string message contain how "rested" the crew members are
	 * and how much stamina they regained.
	 */
	public String rest(int habitability) { //0-3
		String restMessage = "";
		double[] modifiers = {.10, .20, .35, .40};
		int rounded = (int) Math.round(getMaxStamina() * modifiers[habitability]);
		updateCurrentStamina(rounded);
		switch(habitability) {
			case 3: restMessage = (getName() + " is feeling well rested. And now has " + getCurrentStamina() + " stamina.");
					break;
			case 2: restMessage = (getName() + " is feeling moderately rested. And now has " + getCurrentStamina() + " stamina.");
					break;
			case 1: restMessage = (getName() + " is feeling rested. And now has " + getCurrentStamina() + " stamina.");
					break;
			case 0: restMessage = (getName() + "  had a poor sleep due to the planets harsh conditions... And now has " + getCurrentStamina() + " stamina.");
					break;
		}
		return restMessage;
	}
	
	/**
	 * All the crew members rest on the space ship and they all regain some stamina.
	 * 
	 * @return a string message contained how much stamina the crew members regained.
	 */
	public String rest() {
		int rounded = (int) Math.round(getMaxStamina() * .25);
		updateCurrentStamina(rounded);
		return (getName() + " slept alright on the ship. And now has " + getCurrentStamina() + " stamina.");
	}

	/**
	 * Changes a crew member's stat such as maximum health or charisma level.
	 * 
	 * @param changingStat the stat that will be changed.
	 * @param incOrDec if said stat increases or decreases.
	 * @param baseCrew the crew member that will have there stat changed.
	 * @return true if a stat was successfully changed.
	 */
	public boolean changeStat(String changingStat, boolean incOrDec, CrewMember baseCrew) {
		boolean changedStat = true;
		boolean validStatPoints = false;
		if(unallocatedStatPoints > 0 && incOrDec) {
			validStatPoints = true;
		} else if (unallocatedStatPoints <= baseCrew.unallocatedStatPoints && incOrDec == false) {
			validStatPoints = true;
		}
		if(validStatPoints) {
			switch(changingStat) {
			case "health":
				if(incOrDec) {
					if (checkLimitMax(getMaxHealth() + 50, 400) && getMaxHealth() + 50 <= 400) {
						setMaxHealth(getMaxHealth() + 50);
						removeUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				} else {
					if (checkLimitMin(getMaxHealth() - 50, 100) && getMaxHealth() - 50 >= baseCrew.getMaxHealth()) {
						setMaxHealth(getMaxHealth() - 50);
						addUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				}
				break;
			case "stamina":
				if(incOrDec) {
					if (checkLimitMax(getMaxStamina() + 50, 400) && getMaxStamina() + 50 <= 400) {
						setMaxStamina(getMaxStamina() + 50);
						removeUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				} else {
					if (checkLimitMin(getMaxStamina() - 50, 100) && getMaxStamina() - 50 >= baseCrew.getMaxStamina()) {
						setMaxStamina(getMaxStamina() - 50);
						addUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				}
				break;
			case "repair":
				if(incOrDec) {
					if (checkLimitMax(getRepairLevel() + 1, 7) && getRepairLevel() + 1 <= 7) {
						setRepairLevel(getRepairLevel() + 1);
						removeUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				} else {
					if (checkLimitMin(getRepairLevel() - 1, 1) && getRepairLevel() - 1 >= baseCrew.getRepairLevel()) {
						setRepairLevel(getRepairLevel() - 1);
						addUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				}
				break;
			case "scavenging": 
				if(incOrDec) {
					if (checkLimitMax(getScavengingLevel() + 1, 7) && getScavengingLevel() + 1 <= 7) {
						setScavengingLevel(getScavengingLevel() + 1);
						removeUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				} else {
					if (checkLimitMin(getScavengingLevel() - 1, 1) && getScavengingLevel() - 1 >= baseCrew.getScavengingLevel()) {
						setScavengingLevel(getScavengingLevel() - 1);
						addUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				}
				break;	
			case "charisma":
				if(incOrDec) {
					if (checkLimitMax(getCharismaLevel() + 1, 7) && getCharismaLevel() + 1 <= 7) {
						setCharismaLevel(getCharismaLevel() + 1);
						removeUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				} else {
					if (checkLimitMin(getCharismaLevel() - 1, 1) && getCharismaLevel() - 1 >= baseCrew.getCharismaLevel()) {
						setCharismaLevel(getCharismaLevel() - 1);
						addUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				}
				break;
			case "healer": 
				if(incOrDec) {
					if (checkLimitMax(getHealerLevel() + 1, 7) && getHealerLevel() + 1 <= 7) {
						setHealerLevel(getHealerLevel() + 1);
						removeUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				} else {
					if (checkLimitMin(getHealerLevel() - 1, 1) && getHealerLevel() - 1 >= baseCrew.getHealerLevel()) {
						setHealerLevel(getHealerLevel() - 1);
						addUnallocatedStatPoints();
					} else {
						changedStat = false;
					}
				}
				break;
			}
		}
		return changedStat;
	}
	
	/**
	 * Checks if the crew member is already maximisied a particular stat.
	 * 
	 * @param tempLevel the level the stat will increase by.
	 * @param limitMax the maximum the stat can be.
	 * @return true if the stat has not been maximised.
	 */
	public boolean checkLimitMax(int tempLevel, int limitMax) {
		boolean valid = false;
		if (tempLevel <= limitMax) {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * Checks if the crew member is already minimised a particular stat.
	 * 
	 * @param tempLevel the level the stat will increase by.
	 * @param limitMin the minimised the stat can be.
	 * @return true if the stat has not been maximised.
	 */
	public boolean checkLimitMin(int tempLevel, int limitMin) {
		boolean valid = false;
		if (tempLevel >= limitMin) {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * Every time a in game hour passes, crew members with space plague will take damage,
	 * to there current health.
	 * 
	 * @param playerShip the space ship.
	 * @param hoursPassed the hours passed
	 * @throws TakenDamageException if the crew member takes damage,
	 * then said exception is thrown.
	 * @throws DeadCrewException if a crew member dies, then
	 * said exception is thrown.
	 * @throws GameOverException if a parameter that would cause a game over
	 * is reached, then said exception is thrown.
	 */
	public static void spacePlagueDamage(SpaceShip playerShip, int hoursPassed) throws TakenDamageException, DeadCrewException, GameOverException {
		ArrayList<Integer> indicesToBeRemoved = new ArrayList<Integer>();
		ArrayList<CrewMember> crewList = playerShip.getCrewList();
		for(int i=0; i < crewList.size(); i++) {
			CrewMember crew = crewList.get(i);
			if(crew.getHasSpacePlague()) {
				int damage = -1 * (10 * hoursPassed);
				crew.updateCurrentHealth(damage);
				if (crew.checkHealth()) {
					GameEnvironment.updateScore(-10 * damage);
					throw new TakenDamageException(crew.getName() + ": *cough* I don't feel so good captain. " + crew.getCurrentHealth() + "/" + crew.getMaxHealth() + " HP.");
				} else { 
					GameEnvironment.updateScore(-500);
					indicesToBeRemoved.add(i);
				}
				
			}
		}
		playerShip.removeCrewMember(indicesToBeRemoved);
		}
	
	/**
	 * updates the crew members stamina by a defined amount.
	 * 
	 * @param tempStamina the amount of the stamina that will be added
	 * to the crew members current stamina.
	 */
	public void updateCurrentStamina(int tempStamina) {
		currentStamina += tempStamina;
		if(currentStamina > maxStamina) {
			currentStamina = maxStamina;
		} else if(currentStamina < 0) {
			currentStamina = 0;
		}
	}

	public void removeUnallocatedStatPoints() {
		unallocatedStatPoints = getUnallocatedStatPoints() - 1;
	}
	public void addUnallocatedStatPoints() {
		unallocatedStatPoints = getUnallocatedStatPoints() + 1;
	}
	
	public void setItem(Item tempItem) {
		heldItem = tempItem;
	}
	public void setMaxStamina(int tempMaxStamina) {
		maxStamina = tempMaxStamina;
		currentStamina = tempMaxStamina;
	}
	public void setCurrentStamina(int tempCurrentStamina) {
		currentStamina = tempCurrentStamina;
	}
	public void setRepairLevel(int tempRepairLevel) {
		repairLevel = tempRepairLevel;
	}
	public void setScavengingLevel(int tempScavengingLevel) {
		scavengingLevel = tempScavengingLevel;
	}
	public void setCharismaLevel(int tempCharismaLevel) {
		charismaLevel = tempCharismaLevel;
	}
	public void setHealerLevel(int tempHealerLevel) {
		healerLevel = tempHealerLevel;
	}
	public void setHasSpacePlague(boolean spacePlagueChange) {
		hasSpacePlague = spacePlagueChange;
	}
	public Item getHeldItem() {
		return heldItem;
	}
	public int getRepairLevel() {
		return repairLevel;
	}
	public int getScavengingLevel() {
		return scavengingLevel;
	}
	public int getMaxStamina() {
		return maxStamina;
	}
	public int getCurrentStamina() {
		return currentStamina;
	}
	public int getCharismaLevel() {
		return charismaLevel;
	}
	public int getHealerLevel() {
		return healerLevel;
	}
	public int getUnallocatedStatPoints() {
		return unallocatedStatPoints;
	}
	public boolean getHasSpacePlague() {
		return hasSpacePlague;
	}
	public int getDaysWithSpacePlague() {
		return daysWithSpacePlague;
	} 
	
	public String toString() {
		return (getName()	);
	}
}