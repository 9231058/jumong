package jomung.items;

import jomung.Item;
import jomung.Map;
import jomung.Room;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class stoneBreaker extends Item {

	private static final long serialVersionUID = -1421759349720951808L;

	public stoneBreaker() {
		super.setItemType(ItemType.stoneBreaker);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
		int x = player.getCurrentLocationX();
		int y = player.getCurrentLocationY();
		Room room;
		try {
			room = Map.getInstance().getRoomAt(x + 1, y);
			room.setWall(false);
			Map.getInstance().setRoomAt(x + 1, y, room);
			Map.getInstance().setRoomSeeable(x + 1, y, room);
		} catch (IllegalArgumentException exception) {

		}
		try {
			room = Map.getInstance().getRoomAt(x - 1, y);
			room.setWall(false);
			Map.getInstance().setRoomAt(x - 1, y, room);
			Map.getInstance().setRoomSeeable(x - 1, y, room);
		} catch (IllegalArgumentException exception) {

		}
		try {
			room = Map.getInstance().getRoomAt(x, y + 1);
			room.setWall(false);
			Map.getInstance().setRoomAt(x, y + 1, room);
			Map.getInstance().setRoomSeeable(x, y + 1, room);
		} catch (IllegalArgumentException exception) {

		}
		try {
			room = Map.getInstance().getRoomAt(x, y - 1);
			room.setWall(false);
			Map.getInstance().setRoomAt(x, y - 1, room);
			Map.getInstance().setRoomSeeable(x, y - 1, room);
		} catch (IllegalArgumentException exception) {

		}
	}

	@Override
	public String toString() {
		return "stoneBreaker";
	}

}
