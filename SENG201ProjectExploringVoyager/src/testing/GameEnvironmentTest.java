package testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.GameEnvironment;
import matter.*;
public class GameEnvironmentTest {

	@Test
	public void testGenerateStarMapRightBounds() throws Exception {
		GameEnvironment game = new GameEnvironment();
		Location[][] map = game.generateStarMap(3);
		assertEquals(4, map.length);
		assertEquals(2, map[0].length);
		Location[][] map1 = game.generateStarMap(10);
		assertEquals(6, map1.length);
		assertEquals(6, map1[0].length);
	}
	@Test
	public void testGenerateStarMapRightNumberOfAsteroidsMinimumEdge() throws Exception {
		GameEnvironment game = new GameEnvironment();
		Location[][] map = game.generateStarMap(3);
		int numberOfAsteroids = 3;
		int asteroidCounter = 0;
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				if (map[i][j].getCurrentAsteroidBelt() != null) {
					asteroidCounter += 1;
				}
			}
		}
		assertEquals(numberOfAsteroids, asteroidCounter);
	}
	@Test
	public void testGenerateStarMapRightNumberOfAsteroidsMaximumEdge() throws Exception {
		GameEnvironment game = new GameEnvironment();
		Location[][] map = game.generateStarMap(10);
		int numberOfAsteroids = 16;
		int asteroidCounter = 0;
		for (int i=0; i < map.length; i++) {
			for (int j=0; j < map[0].length; j++) {
				if (map[i][j].getCurrentAsteroidBelt() != null) {
					asteroidCounter += 1;
				}
			}
		}
		assertEquals(numberOfAsteroids, asteroidCounter);
	}
	@Test
	public void testGenerateStarMapRightNumberOfPlanets() throws Exception {
		GameEnvironment game = new GameEnvironment();
		Location[][] map = null;
		for (int days=3; days < 10; days++) {
			map = game.generateStarMap(days);
			int planetCounter = 0;
			for (int i=0; i < map.length; i++) {
				for (int j=0; j < map[0].length; j++) {
					if (map[i][j].getCurrentPlanet() != null) {
						planetCounter += 1;
					}
				}
			}
			assertEquals(days - 1, planetCounter);
	}

}
	}
