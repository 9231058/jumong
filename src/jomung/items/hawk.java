package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class hawk extends Item {

	private static final long serialVersionUID = -4383152088730275547L;

	public hawk() {
		super.setItemType(ItemType.hawk);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
		int x = player.getCurrentLocationX();
		int y = player.getCurrentLocationY();
		try {
			player.getMap().setRoomSeeable(x + 1, y,
					player.getMap().getRoomAt(x + 1, y));
		} catch (IllegalArgumentException exception) {
		}
		try {
			player.getMap().setRoomSeeable(x - 1, y,
					player.getMap().getRoomAt(x - 1, y));
		} catch (IllegalArgumentException exception) {
		}
		try {
			player.getMap().setRoomSeeable(x, y + 1,
					player.getMap().getRoomAt(x, y + 1));
		} catch (IllegalArgumentException exception) {
		}
		try {
			player.getMap().setRoomSeeable(x, y - 1,
					player.getMap().getRoomAt(x, y - 1));
		} catch (IllegalArgumentException exception) {
		}
	}

	@Override
	public String toString() {
		return "hawk";
	}

}
