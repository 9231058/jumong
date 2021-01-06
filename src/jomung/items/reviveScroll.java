package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class reviveScroll extends Item {
	private static final long serialVersionUID = 5737408100599403718L;

	public reviveScroll() {
		super.setItemType(ItemType.reviveScroll);
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
		return "reviveScroll";
	}

}
