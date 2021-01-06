package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class bigHealthPotion extends Item {

	private static final long serialVersionUID = 8689844063478627662L;

	public bigHealthPotion() {
		super.setItemType(ItemType.bigHealthPotion);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
		player.increaseHealth(50);
	}

	@Override
	public String toString() {
		return "bigHealthPotion";
	}
}
