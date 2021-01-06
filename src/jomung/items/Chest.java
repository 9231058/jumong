package jomung.items;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import jomung.Item;
import jomung.ItemPutter;
import jomung.Room;
import jomung.chest.ChestMannager;
import jomung.enums.ItemType;
import jomung.movingobject.Player;

public class Chest extends Item {
	private static final long serialVersionUID = -2161250766763301583L;
	private ArrayList<Item> items = new ArrayList<>();
	private UUID id;
	private boolean open = false;

	public Chest() {
		id = UUID.randomUUID();
		ChestMannager.addChest(id);
		Random rand = new Random();
		for (int i = 0; i < ItemType.values().length - 2; i++) {
			for (int j = 0; j < rand.nextInt(3); j++) {
				items.add(ItemPutter.giveItemWithItemType(ItemType.values()[i]));
			}
		}
		super.setItemType(ItemType.Chest);
	}

	public UUID getId() {
		return id;
	}

	public boolean isOpen() {
		return open;
	}

	public ArrayList<Item> getItemsList() {
		open = true;
		return items;
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
	}

	@Override
	public String toString() {
		return "Chest with ID : " + id.toString();
	}
}
