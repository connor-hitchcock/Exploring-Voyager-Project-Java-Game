package allitems;

public class FoodItem extends Item {
	private int fillingFactor;
	
	public FoodItem(String tempName, int tempFillingFactor, int tempValue, String tempDescription) {
		super(tempName, tempValue, 1, tempDescription, "Food");
		fillingFactor = tempFillingFactor;
	}
	
	public int getFillingFactor() {
		return fillingFactor;
	}
}
