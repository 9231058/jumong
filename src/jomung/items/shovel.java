package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class shovel extends Item {

	private static final long serialVersionUID = -6508733411575492176L;

	public shovel() {
		super.setItemType(ItemType.shovel);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
	}

	@Override
	public String toString() {
		return "shovel";
	}

}
