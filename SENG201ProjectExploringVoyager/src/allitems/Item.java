package allitems;

public class Item {
	private String name;
	private int count;
	private int value;
	private String description;
	private String type;
	
	public Item(String tempName, int tempValue, int tempCount, String tempDescription, String tempType) {
		count = tempCount;
		value = tempValue;
		name = tempName;
		description = tempDescription;
		type = tempType;
	}
	
	public String getName() {
		return name;
	}
	public int getCount() {
		return count; //count
	}
	public void setCount(int tempCount) {
		count = tempCount;
	}
	public int getValue() {
		return value;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
	public String toString() {
		return getName();
	}
}
