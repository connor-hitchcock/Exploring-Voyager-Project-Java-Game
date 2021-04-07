package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DeadCrewException;
import exceptions.RandomEventException;
import exceptions.TakenDamageException;
import main.GameEnvironment;
import main.RandomEvents;
import matter.CrewMember;
import matter.SpaceShip;

public class SpaceShipTest {
	
	private RandomEvents events = new RandomEvents();
	@Test
	public void CheckIfEnoughWorkersLittle() throws Exception {
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3));
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 4, 1, 1, 3));
		boolean valid = ship.checkIfEnoughWorkers(10, 2);
		assertTrue(valid);
		valid = ship.checkIfEnoughWorkers(10, 5);
		assertFalse(valid);
		for (CrewMember crew : ship.getCrewList()) {
			crew.setCurrentStamina(10);
		}
		valid = ship.checkIfEnoughWorkers(10, 2);
		assertTrue(valid);
		valid = ship.checkIfEnoughWorkers(10, 3);
		assertFalse(valid);
	}
	
	@Test
	final void FindHighestCharismaCharacterValidationAndRemoval() {
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3));
		ship.addCrewMember(new CrewMember("Charisma", 150, 100, 1, 6, 1, 1, 3));
		ship.addCrewMember(new CrewMember("Pilot 2", 150, 100, 1, 2, 1, 1, 3));
		CrewMember member = ship.findHighestCharismaCharacter();
		assertEquals(ship.getCrewList().get(1), member);
		ArrayList<Integer> removedIndices = new ArrayList<Integer>();
		removedIndices.add(1);
		try {
			ship.removeCrewMember(removedIndices);
		}catch(DeadCrewException e) {}
		member = ship.findHighestCharismaCharacter();
		assertEquals(ship.getCrewList().get(0), member);
	}
	
	@Test
	final void FindHighestCharismaCharacterEqualCharacters() {
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3));
		ship.addCrewMember(new CrewMember("Charisma", 150, 100, 1, 3, 1, 1, 3));
		ship.addCrewMember(new CrewMember("Pilot 2", 150, 100, 1, 3, 1, 1, 3));
		CrewMember member = ship.findHighestCharismaCharacter();
		assertEquals(ship.getCrewList().get(0), member);
	}
	@Test
	final void UseFuelOverLimit() {
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3));
		ship.useFuel(7600);
		assertEquals(7500, ship.getFuelCapacity());
	}
	@Test
	final void UseFuelToLimit() {
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3));
		ship.useFuel(7500);
		assertEquals(0, ship.getFuelCapacity());
	}
	@Test
	final void UseFuelToLimitOverLimit() {
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		ship.addCrewMember(new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3));
		ship.useFuel(7400);
		assertEquals(100, ship.getFuelCapacity());
		ship.useFuel(1000);
		assertEquals(100, ship.getFuelCapacity());
	}
	
	@Test
	final void RestAllMembersUnderLimitAtPlanetHabitability0() {
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(10);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		CrewMember crew2 = new CrewMember("Pilot2", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		ship.addCrewMember(crew2);
		crew1.setCurrentStamina(50);
		assertEquals(50, crew1.getCurrentStamina());
		try {
			ship.restAllMembers(0, events);
		} catch(RandomEventException e) {}
		assertEquals(60, crew1.getCurrentStamina());
	}
	@Test
	final void RestAllMembersUnderLimitAtPlanetHabitability1() {
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(10);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		CrewMember crew2 = new CrewMember("Pilot2", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		ship.addCrewMember(crew2);
		crew1.setCurrentStamina(50);
		assertEquals(50, crew1.getCurrentStamina());
		try {
			ship.restAllMembers(1, events);
		} catch(RandomEventException e) {}
		catch(TakenDamageException e) {}
		assertEquals(70, crew1.getCurrentStamina());
	}
	@Test
	final void RestAllMembersUnderLimitAtPlanetHabitability2() {
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(10);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		CrewMember crew2 = new CrewMember("Pilot2", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		ship.addCrewMember(crew2);
		crew1.setCurrentStamina(50);
		assertEquals(50, crew1.getCurrentStamina());
		try {
			ship.restAllMembers(2, events);
		} catch(RandomEventException e) {}
		catch(TakenDamageException e) {}
		assertEquals(85, crew1.getCurrentStamina());
	}
	@Test
	final void RestAllMembersUnderLimitAtPlanetHabitability3() {
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(10);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		CrewMember crew2 = new CrewMember("Pilot2", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		ship.addCrewMember(crew2);
		crew1.setCurrentStamina(50);
		assertEquals(50, crew1.getCurrentStamina());
		try {
			ship.restAllMembers(3, events);
		} catch(RandomEventException e) {}
		catch(TakenDamageException e) {}
		assertEquals(90, crew1.getCurrentStamina());
	}
	@Test
	final void RestAllMembersUnderLimitOnShip() {
		GameEnvironment game = new GameEnvironment();
		game.setTotalHours(10);
		SpaceShip ship = new SpaceShip("Eagle", 100, 7500, 200, 4, 1);
		game.setPlayerShip(ship);
		CrewMember crew1 = new CrewMember("Pilot", 150, 100, 1, 3, 1, 1, 3);
		CrewMember crew2 = new CrewMember("Pilot2", 150, 100, 1, 3, 1, 1, 3);
		ship.addCrewMember(crew1);
		ship.addCrewMember(crew2);
		crew1.setCurrentStamina(50);
		assertEquals(50, crew1.getCurrentStamina());
		try {
			ship.restAllMembers(events);
		} catch(RandomEventException e) {}
		 catch(TakenDamageException e) {}
		assertEquals(75, crew1.getCurrentStamina());
	}

}
