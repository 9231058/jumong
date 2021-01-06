package jomung.enums;

public enum ItemType {

	smallHealthPotion("smallHealthPotion"), bigHealthPotion("bigHealthPotion"), energyPotion(
			"energyPotion"), reviveScroll("reviveScroll"), bigArrow("bigArrow"), shovel(
			"shovel"), hawk("hawk"), smallArrow("smallArrow"), stoneBreaker(
			"stoneBreaker"), fireArrow("fireArrow"), Chest("Chest"), key("Key");

	private String itemName;

	private ItemType(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

}
