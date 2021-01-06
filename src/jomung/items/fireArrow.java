package jomung.items;

import jomung.Item;
import jomung.Room;
import jomung.Weapon;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class fireArrow extends Item implements Weapon {

	private static final long serialVersionUID = -2658268202404707513L;

	public fireArrow() {
		super.setItemType(ItemType.fireArrow);
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
		int x = player.getCurrentLocationX();
		int y = player.getCurrentLocationY();
		Room room;
		try {
			room = player.getMap().getRoomAt(x + 1, y);
			room.setWall(false);
			player.getMap().setRoomAt(x + 1, y, room);
			player.getMap().setRoomSeeable(x + 1, y, room);
		} catch (IllegalArgumentException exception) {

		}
		try {
			room = player.getMap().getRoomAt(x - 1, y);
			room.setWall(false);
			player.getMap().setRoomAt(x - 1, y, room);
			player.getMap().setRoomSeeable(x - 1, y, room);
		} catch (IllegalArgumentException exception) {

		}
		try {
			room = player.getMap().getRoomAt(x, y + 1);
			room.setWall(false);
			player.getMap().setRoomAt(x, y + 1, room);
			player.getMap().setRoomSeeable(x, y + 1, room);
		} catch (IllegalArgumentException exception) {

		}
		try {
			room = player.getMap().getRoomAt(x, y - 1);
			room.setWall(false);
			player.getMap().setRoomAt(x, y - 1, room);
			player.getMap().setRoomSeeable(x, y - 1, room);
		} catch (IllegalArgumentException exception) {

		}
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
	}

	@Override
	public String toString() {
		return "fireArrow";
	}

}
