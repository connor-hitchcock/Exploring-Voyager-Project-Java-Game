package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import allitems.FoodItem;
import allitems.MedicalItem;
import exceptions.InsufficientStaminaException;
import exceptions.MaximumHealthException;
import exceptions.MaximumStaminaException;
import exceptions.ShipMaximumHealthException;
import exceptions.TakenDamageException;
import main.RandomEvents;
import matter.CrewMember;
import matter.Outpost;
import matter.SpaceShip;


public class CrewMemberTest {

	private SpaceShip playerShip;
	private RandomEvents events;
	private CrewMember statCrew;

	@BeforeEach 
	public void setUp() {
		RandomEvents events = new RandomEvents();
		playerShip = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ArrayList<CrewMember> selectableMembers = new ArrayList<CrewMember>();
		playerShip.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3));
		playerShip.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3));
		playerShip.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3));
		
		
	}

	@Test
	final void healingMemberCuresSpacePlague() {
		playerShip.getCrewList().get(0).setHasSpacePlague(true);
		assertEquals(true, playerShip.getCrewList().get(0).getHasSpacePlague());
		MedicalItem cure = new MedicalItem("Space Plague Cure", 10, 2, 400, " Cures space plague, has 2 uses.", true);
		try {
			playerShip.getCrewList().get(1).heal(cure, playerShip, playerShip.getCrewList().get(0));
			assertEquals(false, playerShip.getCrewList().get(0).getHasSpacePlague());
		} catch(MaximumHealthException e) {
		}
	}

	@Test
	final void usingMedicalItem() {
		MedicalItem healingItem = new MedicalItem("test", 100, 2, 400, " Test healing item", false);
		CrewMember damagedCrew = playerShip.getCrewList().get(0);
		damagedCrew.setCurrentHealth(50);
		CrewMember healer = playerShip.getCrewList().get(1);
		try {
			healer.heal(healingItem, playerShip, damagedCrew);
		} catch (MaximumHealthException e) {
		}
		assertEquals(150, damagedCrew.getCurrentHealth());
		
	}

	@Test
	final void usingMedicalItemOverHealBoundary() {
		MedicalItem healingItem = new MedicalItem("test", 100, 2, 400, " Test healing item", false);
		CrewMember damagedCrew = playerShip.getCrewList().get(0);
		damagedCrew.setCurrentHealth(149);
		CrewMember healer = playerShip.getCrewList().get(1);
		try {
			healer.heal(healingItem, playerShip, damagedCrew);
		} catch (MaximumHealthException e) {
		}
		assertEquals(150, damagedCrew.getCurrentHealth());
	}

	@Test
	final void usingMedicalItemOverHealCloseBoundary() {
		MedicalItem healingItem = new MedicalItem("test", 100, 2, 400, " Test healing item", false);
		CrewMember damagedCrew = playerShip.getCrewList().get(0);
		damagedCrew.setCurrentHealth(51);
		CrewMember healer = playerShip.getCrewList().get(1);
		try {
			healer.heal(healingItem, playerShip, damagedCrew);
		} catch (MaximumHealthException e) {
		}
		assertEquals(150,  damagedCrew.getCurrentHealth());
	}

	@Test
	final void usingFoodItemToBoundary() {
		FoodItem food = new FoodItem("test", 100, 400, " Test food  item");
		CrewMember crew = playerShip.getCrewList().get(0);
		crew.setCurrentStamina(50);
		try {
			crew.feed(food, playerShip);
		} catch (MaximumStaminaException e) {
		}
		assertEquals(100,  crew.getCurrentStamina());
	}

	@Test
	final void generateOutpostInventories() {
		for (int i=0; i < 400; i++) {
			Outpost out = new Outpost(1,1,1,1);
			boolean valid = false;
			if (out.getShopInventory().size() <= 9) {
				valid = true;
			}
			assertEquals(true, valid);
		}
	}

	@Test
	final void testRest() {
		CrewMember crew = playerShip.getCrewList().get(0);
		crew.setCurrentStamina(0);
		crew.rest(3);
		crew.rest(2);
		crew.rest(1);
		crew.rest(0);
		}

	@Test
	final void testRepairOverRepairBoundary() {
		//Repair formula: repairAmount = (repairLevel + 2) * 10
		playerShip.setCurrentHealth(199);
		CrewMember crew = playerShip.getCrewList().get(0);
		try {
			crew.repair(playerShip);
		} catch (InsufficientStaminaException e) {
		} catch (ShipMaximumHealthException e) {
		}
		assertEquals(200, playerShip.getCurrentHealth());
		}

	@Test
	final void testRepair() {
		//Repair formula: repairAmount = (repairLevel + 2) * 10
		playerShip.setCurrentHealth(100);
		CrewMember crew = playerShip.getCrewList().get(0);
		try {
			crew.repair(playerShip);
		} catch (InsufficientStaminaException e) {
		} catch (ShipMaximumHealthException e) {
		}
		assertEquals(130, playerShip.getCurrentHealth());
		}

	@Test
	final void testRepairLimit() {
		//Repair formula: repairAmount = (repairLevel + 2) * 10
		playerShip.setCurrentHealth(170);
		CrewMember crew = playerShip.getCrewList().get(0);
		try {
			crew.repair(playerShip);
		} catch (InsufficientStaminaException e) {
		} catch (ShipMaximumHealthException e) {
		}
		assertEquals(200, playerShip.getCurrentHealth());
		}

	@Test
	final void testRepairCloseToBoundary() {
		//Repair formula: repairAmount = (repairLevel + 2) * 10
		playerShip.setCurrentHealth(169);
		CrewMember crew = playerShip.getCrewList().get(0);
		try {
			crew.repair(playerShip);
		} catch (InsufficientStaminaException e) {
		} catch (ShipMaximumHealthException e) {
		}
		assertEquals(199, playerShip.getCurrentHealth());
		}

	@Test
	final void testSingleUpgrade() {
		CrewMember statCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		CrewMember baseCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		statCrew.changeStat("health", true, baseCrew);
		assertEquals(200, statCrew.getMaxHealth());
		
	}

	@Test
	final void testUpgradeLevelToMax() {
		CrewMember statCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		CrewMember baseCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		 statCrew.changeStat("health", true, baseCrew);
		 statCrew.changeStat("health", true, baseCrew);
		 statCrew.changeStat("health", true, baseCrew);
		 statCrew.changeStat("health", true, baseCrew);
		 statCrew.changeStat("health", true, baseCrew);
		 statCrew.changeStat("health", true, baseCrew);
		assertEquals(400,  statCrew.getMaxHealth());
		 statCrew.changeStat("health", false, baseCrew);
		assertEquals(350,  statCrew.getMaxHealth());
		 statCrew.changeStat("health", true, baseCrew);
		assertEquals(400,  statCrew.getMaxHealth());
	}

	@Test
	final void updateStaminaToMax() {
		CrewMember statCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		statCrew.updateCurrentStamina(-20);
		assertEquals(130, statCrew.getCurrentStamina());
		statCrew.updateCurrentStamina(-140);
		assertEquals(0, statCrew.getCurrentStamina());
		statCrew.updateCurrentStamina(170);
		assertEquals(150, statCrew.getCurrentStamina());
	}

	@Test
	final void testUpdateCurrentHealth() {
		CrewMember crew =  new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3);
		crew.updateCurrentHealth(-10);
		assertEquals(140, crew.getCurrentHealth());
	}

	@Test
	final void testUpgradeLevelToMaxAndBack() {
		CrewMember crew = new CrewMember("", 350, 350, 6, 6, 6, 6, 7);
		CrewMember baseCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		crew.changeStat("health", true, baseCrew);
		crew.changeStat("stamina", true, baseCrew);
		crew.changeStat("scavenging", true, baseCrew);
		crew.changeStat("repair", true, baseCrew);
		crew.changeStat("healer", true, baseCrew);
		crew.changeStat("charisma", true, baseCrew);
		assertEquals(400, crew.getMaxHealth());
		assertEquals(400, crew.getMaxStamina());
		assertEquals(7, crew.getRepairLevel());
		assertEquals(7, crew.getScavengingLevel());
		assertEquals(7, crew.getCharismaLevel());
		assertEquals(7, crew.getHealerLevel());
		crew.changeStat("health", false, baseCrew);
		crew.changeStat("stamina", false, baseCrew);
		crew.changeStat("scavenging", false, baseCrew);
		crew.changeStat("repair", false, baseCrew);
		crew.changeStat("healer", false, baseCrew);
		crew.changeStat("charisma", false, baseCrew);
		assertEquals(350, crew.getMaxHealth());
		assertEquals(350, crew.getMaxStamina());
		assertEquals(6, crew.getRepairLevel());
		assertEquals(6, crew.getScavengingLevel());
		assertEquals(6, crew.getCharismaLevel());
		assertEquals(6, crew.getHealerLevel());
	}

	@Test
	final void testUpgradeLevel() {
		CrewMember crew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		CrewMember baseCrew = new CrewMember("", 150, 150, 1, 1, 1, 1, 7);
		crew.changeStat("health", true, baseCrew);
		crew.changeStat("health", true, baseCrew);
		crew.changeStat("health", true, baseCrew);
		crew.changeStat("health", true, baseCrew);
		crew.changeStat("health", true, baseCrew);
		crew.changeStat("health", true, baseCrew);
		assertEquals(400, crew.getMaxHealth());
		crew.changeStat("health", false, baseCrew);
		assertEquals(350, crew.getMaxHealth());
		crew.changeStat("health", true, baseCrew);
		assertEquals(400, crew.getMaxHealth());
		
	}

}
