	package allitems;

public class CrewItem extends Item {
	private String upgradeType;
	
	public CrewItem(String tempName, int tempValue, String tempUpgradeType, String tempDescription) {
		super(tempName, tempValue, 1, tempDescription, "Crew Item");
		upgradeType = tempUpgradeType;
	}
	
	public String getUpgradeType() {
		return upgradeType;
	}

}
