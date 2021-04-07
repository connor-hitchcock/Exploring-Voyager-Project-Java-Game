package allitems;

public class MedicalItem extends Item {
	private int healingFactor;
	private boolean spacePlagueCure;

	public MedicalItem(String tempName, int tempHealingFactor, int tempUsesRemaining, int tempValue, String tempDescription, boolean tempSpacePlagueCure) {
		super(tempName, tempValue, tempUsesRemaining, tempDescription, "Medical");
		healingFactor = tempHealingFactor;
		spacePlagueCure = tempSpacePlagueCure;
		}
	
	public int getHealingFactor() {
		return healingFactor;
	}
	public boolean getSpacePlagueCure() {
		return spacePlagueCure;
	}
}
