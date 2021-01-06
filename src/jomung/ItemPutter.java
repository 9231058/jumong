package jomung;

import java.util.Random;

import jomung.enums.ItemType;

public final class ItemPutter {

	private ItemPutter() {
	}

	public static Item giveItemWithItemType(ItemType itemType) {
		try {
			return (Item) Class.forName(
					"jomung.items." + itemType.getItemName()).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException exception) {
			exception.printStackTrace();
			return null;
		}

	}

	public static void putRandomItem(Room room) {
		Random rand = new Random();
		if (!room.isWall()) {
			for (int i = 0; i < ItemType.values().length; i++) {
				for (int j = 0; j < rand.nextInt(3); j++) {
					room.addItem(ItemPutter.giveItemWithItemType(ItemType
							.values()[i]));
				}
			}
		}
	}
}
