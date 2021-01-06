package jomung.items;

import java.util.UUID;

import jomung.Item;
import jomung.Room;
import jomung.chest.ChestMannager;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class Key extends Item {

	private static final long serialVersionUID = 5498342389652846708L;
	private UUID id = null;

	public Key() {
		id = ChestMannager.addKey();
		super.setItemType(ItemType.key);
	}

	private boolean canOpen(Chest chest) {
		if (id == null) {
			return true;
		} else if (id.compareTo(chest.getId()) == 0) {
			return true;
		}
		return false;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
		for (int i = 0; i < playerRoom.getChestsList().size(); i++) {
			if (this.canOpen(playerRoom.getChestsList().get(i))) {
				playerRoom.getItemsList().addAll(
						playerRoom.getChestsList().get(i).getItemsList());
				playerRoom.getItemsList().remove(
						playerRoom.getChestsList().get(i));
				playerRoom.getChestsList().remove(i);
			}
		}
	}

	@Override
	public String toString() {
		if (id != null) {
			return "Key With ID : " + id.toString();
		} else {
			return "Key With ID : 0";
		}
	}
}
