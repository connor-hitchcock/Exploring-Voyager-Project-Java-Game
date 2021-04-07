package matter;

/**
 * 
 * This class that is a 2D grid star map of a given location objects and holds all
 * information associated with objects at said location. Such as Planets and
 * Asteroid Belts.
 *
 */

public class Location extends Place{
	private Planet currentPlanet;
	private AsteroidBelt currentAsteroidBelt;
	
	/**
	 * The constructor for the location class
	 * 
	 * @param tempCoordinateX the X Coordinate of the object in the star map.
	 * @param tempCoordinateY the Y Coordinate of the object in the star map.
	 * @param tempPlanet the name of the planet in the star map.
	 * @param tempName the name of the object in the star map.
	 */
	public Location(int tempCoordinateX, int tempCoordinateY, Planet tempPlanet) {
		super(tempCoordinateX, tempCoordinateY);
		currentPlanet = tempPlanet;
	}
	
	public Location(int tempCoordinateX, int tempCoordinateY, String tempName) {
		super(tempCoordinateX, tempCoordinateY, tempName);
	}
	
	public Location(int tempCoordinateX, int tempCoordinateY) {
		super(tempCoordinateX, tempCoordinateY, "Deep Space");
	}
	
	/**
	 * Checks if there is an object in a given position in the star map.
	 * 
	 * @return returns true if an object is in said position.
	 */
	public boolean checkEmpty() {
		boolean isEmpty = true;
		if(getCurrentAsteroidBelt() != null) {
			isEmpty = false;
		}
		if (getCurrentPlanet() != null) {
			isEmpty = false;
		}
		return isEmpty;
	}
	
	public void setTrueName() {
		setName("Asteroid Belt");
	}
	public void setCurrentPlanet(Planet tempPlanet) {
		currentPlanet = tempPlanet;
	}
	
	public Planet getCurrentPlanet() {
		return currentPlanet;
	}
	public AsteroidBelt getCurrentAsteroidBelt() {
		 return currentAsteroidBelt;
	}
	public void setCurrentAsteroidBelt(AsteroidBelt tempAsteroidBelt) {
		 currentAsteroidBelt = tempAsteroidBelt;
	}
	
	public String toString() {
		return getName();
	}
}
