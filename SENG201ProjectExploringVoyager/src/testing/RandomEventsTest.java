package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import allitems.FoodItem;
import exceptions.RandomEventException;
import main.GameEnvironment;
import main.RandomEvents;
import matter.CrewMember;
import matter.SpaceShip;

public class RandomEventsTest {

	@Test
	public void checkAllTraverseOutputs() throws Exception {
		RandomEvents events = new RandomEvents();
		for (int i=0; i < 1000; i++) {
			GameEnvironment game = new GameEnvironment();
			game.setTotalHours(100);
			SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
			game.setPlayerShip(ship);
			CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
			CrewMember crew2 = new CrewMember("Pilot2", 150, 100, 1, 3, 1, 1, 3);
			ship.addCrewMember(crew1);
			ship.addCrewMember(crew2);
			try {
			events.doRandomEvent(ship, "traverse");
			}catch (RandomEventException e) {}
		}
	}
	
	@Test
	public void checkHitByAsteroid() throws Exception {
		RandomEvents events = new RandomEvents();
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(100);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		try {
		events.hitByAsteroid(ship);
		} catch (RandomEventException e) {}
		}
	
	@Test
	public void checkSpaceHeroCurrencyGain() throws Exception {
		RandomEvents events = new RandomEvents();
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(100);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		int currency = ship.getCurrency();
		try {
		events.spaceHero(ship);
		} catch (RandomEventException e) {}
		boolean gain = false;
		if (ship.getCurrency() > currency) {
			gain = true;
		}
		assertTrue(gain);
		}
	

	@Test
	public void checkAlienPirateStealsItem() throws Exception {
		RandomEvents events = new RandomEvents();
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(100);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		ship.addItem(new FoodItem("Milky Way", 15, 150, "test item"));
		int inventoryLength = ship.getInventoryLength(ship.getItemInventory());
		try {
		events.alienPirate(ship);
		} catch (RandomEventException e) {}
		boolean stolen = false;
		if (ship.getInventoryLength(ship.getItemInventory()) < inventoryLength) {
			stolen = true;
		}
		assertTrue(stolen);
		}
	
	@Test
	public void checkAlienPirateStealsNothing() throws Exception {
		RandomEvents events = new RandomEvents();
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(100);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		int inventoryLength = ship.getInventoryLength(ship.getItemInventory());
		try {
		events.alienPirate(ship);
		} catch (RandomEventException e) {}
		boolean stolen = false;
		if (ship.getInventoryLength(ship.getItemInventory()) < inventoryLength) {
			stolen = true;
		}
		assertFalse(stolen);
		}
	}

