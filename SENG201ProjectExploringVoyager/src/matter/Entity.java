package matter;

import java.util.ArrayList;

import allitems.*;
import exceptions.DeadCrewException;
import main.GameEnvironment;

/**
 * 
 * ..
 *
 */

public class Entity extends Place{
	private int maxHealth;
	private int currentHealth;
	private int itemInventoryLimit;
	private int starPieceInventoryLimit;
	private Item[] itemInventory;
	private StarPiece[] starPieceInventory;
	
	
	public Entity(int tempMaxHealth, int tempItemInventoryLimit, int tempCoordinateX, int tempCoordinateY, String tempName) {
		super(tempCoordinateX, tempCoordinateY, tempName);
		maxHealth = tempMaxHealth;
		currentHealth = tempMaxHealth;
		itemInventoryLimit = tempItemInventoryLimit;
		itemInventory = new Item[tempItemInventoryLimit];
	}
	
	public Entity(int tempMaxHealth, int tempItemInventoryLimit, String tempName) {
		super(1, 1, tempName);
		maxHealth = tempMaxHealth;
		currentHealth = tempMaxHealth;
		itemInventoryLimit = tempItemInventoryLimit;
		itemInventory = new Item[tempItemInventoryLimit];
	}

	public void updateCurrentHealth(int changeHealth) {
		setCurrentHealth((getCurrentHealth() + changeHealth));
		if(currentHealth <= 0) {
			setCurrentHealth(0);
		} else if(currentHealth >= getMaxHealth()) {
			setCurrentHealth(getMaxHealth());
		}
	}

	public boolean checkHealth() {
		boolean alive = true;
		if (getCurrentHealth() <= 0) {
			alive = false;
		}
		return alive;
	}
	public void setMaxHealth(int tempMaxHealth) {
		maxHealth = tempMaxHealth;
		currentHealth = tempMaxHealth;
	}
	public void setCurrentHealth(int tempHealth) {
		currentHealth = tempHealth;
	}
	public void setItemInventory(Item[] tempInventory) {
		itemInventory = tempInventory;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getItemInventoryLimit() {
		return itemInventoryLimit;
	}
	public int getCurrentHealth() {
		return currentHealth;
	} 
	
	public int getInventoryLength(Item[] itemInventory) {
	    int count = 0;
	    for(Item item : itemInventory) {
	        if (item != null) {
	            count += 1;
	        }
	    }
	    return count;
	}
	
	public boolean addItem(Item item) {
		boolean itemAdded = false;
		if (getInventoryLength(getItemInventory()) + 1 > getItemInventoryLimit()) {
			System.out.println("Not enough space!");
		} else {
			int i = 0;
			while ((i < getItemInventoryLimit()) && (itemAdded == false)) {
			if (itemInventory[i] == null) {
				itemInventory[i] = item;
				itemAdded = true;
			}
			i += 1;
		}
		}
		return itemAdded;
	}
		
	public Item[] getItemInventory() {
		return itemInventory;
	}
	
	public String toString() {
		return getName();
	}
}
