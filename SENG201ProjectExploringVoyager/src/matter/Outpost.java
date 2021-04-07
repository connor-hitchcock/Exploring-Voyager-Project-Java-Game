package matter;

import java.util.*;
import allitems.Item;

/**
 * 
 * This class implements the Outposts that will be placed on the planets,
 * and deals with all the interacts with said outpost such as buying items.
 *
 */

public class Outpost extends PlanetLocation{
	private ArrayList<Item> shopInventory = new ArrayList<Item>();
	private ArrayList<Integer> adjustedPrices = new ArrayList<Integer>();
	private ArrayList<CrewMember> crewAtOutpost = new ArrayList<CrewMember>(); 
	private int outpostHabitability;
	private int outpostInfrastructure;
	
	/**
	 * The constructor for the Outpost.
	 * 
	 * @param tempXCoord the X coordinate of the Outpost on the planet's map grid.
	 * @param tempYCoord the Y coordinate of the Outpost on the planet's map grid.
	 * @param tempHabitability the habitability of the planet.
	 * @param tempInfrastructure the infrastructure of the planet.
	 */
	public Outpost(int tempXCoord, int tempYCoord, int tempHabitability, int tempInfrastructure) {
		super(tempXCoord, tempYCoord);
		outpostHabitability = tempHabitability;
		outpostInfrastructure = tempInfrastructure;
		generateItems();
	}
	public Outpost(int tempXCoord, int tempYCoord, int tempHabitability, int tempInfrastructure, String tempOutpostName) {
		super(tempXCoord, tempYCoord, tempOutpostName);
		outpostHabitability = tempHabitability;
		outpostInfrastructure = tempInfrastructure;
		generateItems();
	}
	
	/**
	 * Generates all the items that can be brought from the outpost.
	 * 
	 */
	public void generateItems() {
		// creates items and then there prices based of the stats of the crew member 
		Random itemModifier = new Random(); 
	    int extraItems = itemModifier.nextInt(5) - 2;
		int numOfItems = getOutpostHabitability() + extraItems + 4;
		for (int i=0; i < numOfItems; i++) { 
			int itemID = itemModifier.nextInt(9);
			switch(itemID) {
				case 0:	
				case 1:
				case 2:
				case 3: addToShopInventory(generateFuelCanister());	
						break;
				case 4:
				case 5: addToShopInventory(generateMedicalItem());
						break;
				case 6: 
				case 7: addToShopInventory(generateFoodItem());
						break;	
				case 8: addToShopInventory(generateCrewItem()); 
						break;
			}
		}
	}

	/**
	 * Calculates the final price of the item that can be purchased,
	 * is modifiable by the crew member charisma level.
	 * 
	 * @param playerShip the space ship.
	 * @param item the given item to be purchased.
	 * @return
	 */
	public long calculateFinalItemPrice(SpaceShip playerShip, Item item) {
		CrewMember highestCharismaCrew = playerShip.findHighestCharismaCharacter();
		double priceModifier = calculatePriceModifier(highestCharismaCrew);
		return calculatePrice(item, priceModifier);
	}
	
	/**
	 * Outputs the message with the information of the transaction.
	 * 
	 * @param playerShip the space ship.
	 * @param item the purchased item.
	 * @param total the index of the shop item.
	 * @param itemPrice the price of the purchased item.
	 * @return a string message containing information about the transaction.
	 */
	public String completeTransaction(SpaceShip playerShip, Item item, int total, long itemPrice) {
		String finalMessage = "";
		boolean added = false;
		if (playerShip.getCurrency() >= itemPrice) {
			added = playerShip.addItem(item);
			if (added == false) {
				CrewMember crewAddedTo = playerShip.addItemToCrewMember(item); //TODO
				if (crewAddedTo != null) {
					finalMessage = ("THE " + item.getName().toUpperCase() + " WAS DEPOSITED INTO " + crewAddedTo.getName().toUpperCase() + "'S INVENTORY.");
				} 	
			} else {
				finalMessage = ("THE " + item.getName().toUpperCase() + " WAS DEPOSITED INTO YOUR SHIPS INVENTORY. THANK YOU LIFEFORM.");
				}
			if (added == true) {
				playerShip.updateCurrency((int) Math.round(-1 * itemPrice));
				removeItemFromShop(total);
			} else {
				finalMessage = ("N0-SPAC3");
			}
		} else {
			finalMessage = ("N0-MUNS");
		}
		return finalMessage;
	}
	
	/**
	 * Displays all the purchasable items the crew member can buy.
	 * 
	 * @param currentMember the crew member that the items purchased will be given too.
	 */
	public void displayItems(CrewMember currentMember) {
		double priceModifier = calculatePriceModifier(currentMember);
		ArrayList<Item> outpostInventory = getShopInventory();
		for (int i=0; i < outpostInventory.size(); i++) {
			Item currentItem = getShopInventory().get(i);
			long modifiedPrice = calculatePrice(currentItem, priceModifier);
			String itemOutput = "(" + (i + 1) + ") " + outpostInventory.get(i).getName() + ", price: " + modifiedPrice;
		}
	}
	
	/**
	 * Removes the item from the Outpost's shop inventory.
	 * 
	 * @param itemIndex the item index of the item to be removed.
	 */
	public void removeItemFromShop(int itemIndex) {
		ArrayList<Item> shopInventory = getShopInventory();
		shopInventory.set(itemIndex, null);
		
		setShopInventory(shopInventory);
	}
	
	/**
	 * Calculates the modified price of the purchased item,
	 * based on the crew member's charisma level.
	 * 
	 * @param currentMember the crew member with the highest charisma
	 * level on the space ship.
	 * @return
	 */
	public double calculatePriceModifier(CrewMember currentMember) {
		double priceModifier = 1;
		if (currentMember.getCharismaLevel() != 1) {
			priceModifier = (-(10/9) * (Math.pow(currentMember.getCharismaLevel() - 7, 2)) + 40) / 100;
		}
		return priceModifier;
	}
	
	/**
	 * Calculates the price of the purchasable item.
	 * 
	 * @param item the given item to have a to be determined price.
	 * @param priceModifier the price modifier applied to the purchasable item.
	 * @return the price of the purchasable item.
	 */
	public long calculatePrice(Item item, double priceModifier) {
		return Math.round(priceModifier * item.getValue());
	}
	
	public void addToShopInventory(Item tempItem) {
		shopInventory.add(tempItem);
	}
	public void setShopInventory(ArrayList<Item> tempList) {
		shopInventory = tempList;
	}
	public ArrayList<Item> getShopInventory() {
		return shopInventory;
	}
	public ArrayList<Integer> getAdjustedPrices() {
		return adjustedPrices;
	}
	public ArrayList<CrewMember> getCrewAtOutpost() {
		return crewAtOutpost;
	}
	
	public int getOutpostInfrastructure() {
		return outpostInfrastructure;
	}
	public int getOutpostHabitability() {
		return outpostHabitability;
	}
	
	public String toString() {
		return "There is an outpost called " + getName();
		
	}
}
