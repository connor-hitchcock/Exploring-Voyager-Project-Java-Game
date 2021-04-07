package matter;

/**
 * 
 * The abstract class for all objects that interact and will be put on the star map.
 *
 */

public abstract class Place {
	private int coordinateX, coordinateY;
	private String name;
	
	/**
	 * The constructor for the place class
	 * 
	 * @param tempCoordinateX the X coordinate of the object on the star map.
	 * @param tempCoordinateY the Y coordinate on the object on the star map.
	 * @param tempName the name of the object.
	 */
	public Place(int tempCoordinateX, int tempCoordinateY, String tempName) {
		coordinateX = tempCoordinateX;
		coordinateY = tempCoordinateY;
		name = tempName;
	}
	
	public Place(int tempCoordinateX, int tempCoordinateY) {
		coordinateX = tempCoordinateX;
		coordinateY = tempCoordinateY;
	}
	
	public int getCoordinateX() {
		return coordinateX;
	}
	public int getCoordinateY() {
		return coordinateY;
	}
	public String getName() {
		return name;
	}
	
	public void setCoordinates(int tempCoordinateX, int tempCoordinateY) {
		coordinateX = tempCoordinateX;
		coordinateY = tempCoordinateY;
	}
	public void setName(String tempName) {
		name = tempName;
	}
	
	public abstract String toString();
}
