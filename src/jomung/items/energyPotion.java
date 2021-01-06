package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class energyPotion extends Item {

	private static final long serialVersionUID = -899370665315645765L;

	public energyPotion() {
		super.setItemType(ItemType.energyPotion);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
		player.increaseEnergy(10);
	}

	@Override
	public String toString() {
		return "energyPotion";
	}

}
