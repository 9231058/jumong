package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class smallHealthPotion extends Item {

	private static final long serialVersionUID = -8752649742304503466L;

	public smallHealthPotion() {
		super.setItemType(ItemType.smallHealthPotion);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
		player.increaseHealth(20);
	}

	@Override
	public String toString() {
		return "smallHealthPotion";
	}
}
