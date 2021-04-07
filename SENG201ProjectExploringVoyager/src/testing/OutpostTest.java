package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import matter.Outpost;
import matter.Planet;

class OutpostTest {
	
	
	@Test
	public void generateOutpostAndItems() throws Exception {
		Random planetRandomiser = new Random();
		for (int i=0; i < 10000; i++) {
			Planet planet = new Planet(planetRandomiser.nextInt(4), planetRandomiser.nextInt(4), 1, 1, "planet");
			for (int j=0; j < 2; j++) {
				for (int k=0; k < 2; k++) {
					if (planet.getLandMap()[j][k].getCurrentOutpost() != null) {
						boolean valid = false;
						Outpost out = planet.getLandMap()[j][k].getCurrentOutpost();
						if (out.getShopInventory().size() > 0) {
							valid = true;
						}
						assertTrue(valid);
					}
			}
	}

	}
	}
}
