package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import matter.*;

import org.junit.jupiter.api.Test;

class PriceModifierTest {

	private Outpost out;
	
	@BeforeEach
	void setUp() {
		out = new Outpost(1,1,1,1); 
	}
		
	@Test
	final void testLowerGeneratePriceModifier() {
		CrewMember testCrew = new CrewMember("1 charisma", 1,1,1,1,1,1,1);
		assertEquals(1, out.calculatePriceModifier(testCrew));
	}
	
	@Test
	final void testUpperGeneratePriceModifier() {
		CrewMember testCrew = new CrewMember("7 charisma", 1,1,1,7,1,1,1);
		Outpost out = new Outpost(1,1,1,1);
		assertEquals(0.4, out.calculatePriceModifier(testCrew));
	}

}
