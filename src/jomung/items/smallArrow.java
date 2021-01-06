package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.Weapon;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class smallArrow extends Item implements Weapon {

	private static final long serialVersionUID = 3504875481372560180L;

	public smallArrow() {
		super.setItemType(ItemType.smallArrow);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
		if (index < playerRoom.getEnemiesList().size()) {
			player.increaseHealth(10);
			playerRoom.getEnemiesList().get(index).costHealth();
		} else {
			index -= playerRoom.getEnemiesList().size();
			playerRoom.getPlayersList().get(index).costHealth();
		}
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
	}

	@Override
	public String toString() {
		return "smallArrow";
	}

}
