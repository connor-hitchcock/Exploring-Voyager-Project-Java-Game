	package allitems;

public class FuelCanister extends Item {
	private int fuellingFactor;
	
	public FuelCanister(String tempName, int tempFuellingFactor, int tempValue, String tempDescription) {
		super(tempName, tempValue, 1, tempDescription, "Fuel Canister");
		fuellingFactor = tempFuellingFactor;
	}
	
	public int getFuellingFactor() {
		return fuellingFactor;
	}
}
