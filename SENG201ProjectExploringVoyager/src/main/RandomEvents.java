package main;

import java.util.ArrayList;
import java.util.Random;

import allitems.Item;
import exceptions.RandomEventException;
import matter.CrewMember;
import matter.SpaceShip;

/**
 * 
 * This class deals with all the random events and holds them all.
 *
 */

public class RandomEvents {
	
	/**
	 * Calls all random events that can be done during said scenario and random sees if any occur given a percentage.
	 * 
	 * @param playerShip the space space.
	 * @param identifier the current action the space ship is doing, for example,
	 * traversing or exploring or traveling through an asteroid belt.
	 */
    public void doRandomEvent(SpaceShip playerShip, String identifier) {
        Random eventSelector = new Random();
        int event1Chance = eventSelector.nextInt(100); // Event: Hit By Asteroid    | 10%  | Traverse | Damage ship - 80-120HP                 | Instead 50% if in asteroid belt
        int event2Chance = eventSelector.nextInt(100); // Event: Thruster Explodes  | 1%  | Traverse | Max Ship Damage (1HP Left)
        int event3Chance = eventSelector.nextInt(100); // Event: Debris on Landing  | 20% | Landing  | 20-60% Shield Damage
        int event4Chance = eventSelector.nextInt(100); // Event: Space Hero         | 10% | Landing  | Gain 400-600 Currency
        int event5Chance = eventSelector.nextInt(100); // Event: Alien Pirate       | 10% | Traverse | Lose random item
        											   // Event: Space Plague       | 3%  | Resting  | Random Crew Member Gets Space Plague
        if(identifier == "traversing") {
            if(event1Chance >= 1 && event1Chance <= 10) {
                hitByAsteroid(playerShip);
            } else if(event2Chance == 0) {
                thrusterExplodes(playerShip);
            } else if(event5Chance <= 9) {
            	alienPirate(playerShip);
            }
        } else if(identifier == "landing") {
            if(event3Chance >= 0 && event3Chance <= 19) {
                debrisOnLanding(playerShip);
            } else if(event4Chance >= 0 && event4Chance <= 9) { //effectively less than 10% as it happens only if the debrisOnLanding event does not happen
                spaceHero(playerShip);
            }
        } else if(identifier == "asteroidBelt") {
            if(event1Chance >= 0 && event4Chance <= 74) {
                hitByAsteroid(playerShip);
            }
        }
    }

    /**
     * The space ship was hit by an asteroid and takes damage.
     * 
     * @param playerShip the space ship.
     * @throws RandomEventException random event occurs, throws said exception.
     */
    public void hitByAsteroid(SpaceShip playerShip) throws RandomEventException {
        Random damageSelector = new Random();
        int damageModifier = (damageSelector.nextInt(10) + 1) * 10;
        playerShip.damageShieldCapacity(damageModifier);
        playerShip.takenDamage(damageModifier);
        throw new RandomEventException("***An asteroid hit the ship!***");
    }

    /**
     * The space ship's thruster explodes and your space ship takes a lot of damage.
     * 
     * @param playerShip the space ship.
     * @throws RandomEventException
     */
    public void thrusterExplodes(SpaceShip playerShip) throws RandomEventException {
        playerShip.setCurrentHealth(1);
        int damageModifier = playerShip.getMaxHealth() + playerShip.getMaxShieldCapacity() - 1;
        playerShip.takenDamage(damageModifier);
        throw new RandomEventException("***Our engine exploded!***");
    }

    /**
     * The space ship has been hit by debris whilst landing on a planet, and takes damage.
     * 
     * @param playerShip the space ship.
     * @throws RandomEventException random event occurs, throws said exception.
     */
    public void debrisOnLanding(SpaceShip playerShip) throws RandomEventException {
        Random damageSelector = new Random();
        int damageModifier =  (damageSelector.nextInt(5) + 1) * -10;
        playerShip.updateShieldCapacity(damageModifier);
        playerShip.takenDamage(damageModifier);
        throw new RandomEventException("***We hit some debris during landing!***");
        
    }

    /**
     * The inhabitants of the planet see you and your space ship as a god,
     * and are therefore treated like one and given some goodies.
     * 
     * @param playerShip the space ship.
     * @throws RandomEventException random event occurs, throws said exception.
     */
    public void spaceHero(SpaceShip playerShip) throws RandomEventException {
        Random currencySelector = new Random();
        int givenCurrency = currencySelector.nextInt(21);
        int modifiedCurrency = ((givenCurrency+40) * 10); 
        GameEnvironment.updateScore(200);
        playerShip.updateCurrency(modifiedCurrency);
        throw new RandomEventException("***We were hailed as heroes when we landed and recieved " + modifiedCurrency + " space credits***");
        
    }
    
    /**
     * A crew member has become infected with space plague.
     * 
     * @param crew the given crew member.
     * @param crewList the crew members aboard the space ship.
     * @throws RandomEventException random event occurs, throws said exception.
     */
    public void spacePlague(CrewMember crew, ArrayList<CrewMember> crewList) throws RandomEventException {
        Random sickSelector = new Random();
        int membersWithPlague = 0;
        for (int i=0; i < crewList.size(); i++) {
        	membersWithPlague += 1;
        }
        int sickInt = sickSelector.nextInt(99);
    	if (sickInt <= (3 + (2 * membersWithPlague))) {
    		if (crew.getHasSpacePlague() == false) {
    		crew.setHasSpacePlague(true);
    		throw new RandomEventException(crew.getName() + " is showing symptoms of Space Plague!");
        	}
    	}
    }
    
    /**
     * 
     * 
     * @param playerShip the space ship.
     */
    public void alienPirate(SpaceShip playerShip) {
    	GameEnvironment.updateScore(-50);
        Item[] shipInv = playerShip.getItemInventory();
        Random slotSelector = new Random();
        int invLength = playerShip.getInventoryLength(shipInv);
        if(invLength > 0) {
        	int selectedSlot = slotSelector.nextInt(invLength);
            String stolenItem = shipInv[selectedSlot].getName();
            shipInv[selectedSlot] = null;
            throw new RandomEventException("Pizza King: HAR HAR HAR THE PIZZA CUTTER SPACE PIRATES HAVE STEALED YE' BOOTY! SAY GOODBYE TO YE " + stolenItem);
        } else {
        	throw new RandomEventException("Pizza King: ARGGG, YOU GOT NOTHING FOR ME MATEY!");
        }
    }
}
    


    
    