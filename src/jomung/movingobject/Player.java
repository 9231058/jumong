package jomung.movingobject;

import java.util.ArrayList;
import java.util.List;

import jomung.Item;
import jomung.Map;
import jomung.Room;
import jomung.Weapon;
import jomung.enums.Direction;
import jomung.enums.ItemType;

public class Player extends MovingObject {
	private static final long serialVersionUID = 4277492723564215032L;
	private int maxInventorySize = 50;
	private int energy = 100;
	private int score = 0;
	private ArrayList<Item> items = new ArrayList<>();

	public Player(int x, int y) {
		super(x, y);
		Room room = Map.getInstance().getRoomAt(x, y);
		room.addMovingObject(this);
		this.room = room;
		Map.getInstance().setRoomAt(x, y, room);
	}

	@Override
	public void ready() {
		super.ready();
		int index = room.getPlayersList().indexOf(this);
		Player player = room.getPlayersList().get(index);
		energy = player.energy;
		score = player.score;
		maxInventorySize = player.maxInventorySize;
		items = player.items;
	}

	public int getScore() {
		return score;
	}

	@Override
	public void move(Direction direction) {
		costEnergy(5);
		super.move(direction);
		score += room.getScore();
	}

	@Override
	public void costHealth() {
		this.health -= 10;
		if (this.health <= 0) {
			checkRecovery();
		}
		update();
	}

	public void getItem(String itemName) {
		List<Item> items = room.getItemsList();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getString().equalsIgnoreCase(itemName)
					&& this.items.size() + 1 <= maxInventorySize) {
				this.items.add(items.get(i));
				items.remove(i);
				break;
			}
		}
		update();
	}

	public void useItem(String itemName) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getString().equalsIgnoreCase(itemName)) {
				if (items.get(i) instanceof Weapon)
					return;
				this.items.get(i).useItem(this, room);
				this.items.remove(i);
				break;
			}
		}
		update();
	}

	public void useWeapon(String weaponName, int index) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getString().equalsIgnoreCase(weaponName)) {
				items.get(i).useItem(this, room, index);
				items.remove(i);
				break;
			}
		}
		update();
	}

	public void dropItem(String itemName) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getString().equalsIgnoreCase(itemName)) {
				room.addItem(items.get(i));
				this.items.remove(i);
				break;
			}
		}
		update();
	}

	public void shovel(int x, int y) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getString().equalsIgnoreCase("shovel")) {
				items.remove(i);
				break;
			}
		}
		update();
		if (Math.abs(currentLocation.x - x) < 5) {
			if (Math.abs(currentLocation.y - y) < 5) {
				setLocation(x, y);
			}
		}
		update();
	}

	public void autoWar() {
		if (room.getItemsList().size() != 0) {
			useWeapon(ItemType.smallArrow.getItemName(), 0);
		} else {
			return;
		}
	}

	public boolean isWinner() {
		if (Map.getInstance().getEndPoint().x == getCurrentLocationX()) {
			if (Map.getInstance().getEndPoint().y == getCurrentLocationY()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Item> getItemsList() {
		return items;
	}

	public int getEnergy() {
		return energy;
	}

	public void increaseEnergy(int amount) {
		energy += amount;
		update();
	}

	public boolean haveEnergy() {
		if (energy > 0) {
			return true;
		}
		return false;
	}

	public boolean haveEnemies() {
		if (room.getEnemiesList().size() > 0) {
			return true;
		}
		return false;
	}

	public int getMaxInventorySize() {
		return maxInventorySize;
	}

	public void setMaxInventorySize(int maxInventorySize) {
		this.maxInventorySize = maxInventorySize;
	}

	private void costEnergy(int cost) {
		if (items.size() >= (double) ((3 / 4) * maxInventorySize)) {
			energy -= cost;
		}
		energy -= cost;
	}

	private void checkRecovery() {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getString().equalsIgnoreCase("reviveScroll")) {
				this.items.get(i).useItem(this, room);
				this.items.remove(i);
			}
		}
		update();
	}
}
