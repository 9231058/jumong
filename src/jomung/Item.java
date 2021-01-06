package jomung;

import java.io.Serializable;

import javax.swing.ImageIcon;

import jomung.enums.ItemType;
import jomung.movingobject.Player;

public abstract class Item implements Serializable {

	private static final long serialVersionUID = 7351084299435775002L;
	private ItemType itemType;

	abstract public void useItem(Player player, Room playerRoom, int index);

	abstract public void useItem(Player player, Room playerRoom);

	public ImageIcon getImage() {
		return new ImageIcon("icons/" + itemType.getItemName() + ".png");
	}

	public String getString() {
		return itemType.getItemName();
	}

	protected void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

}
