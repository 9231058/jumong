package jomung.items;

import java.util.List;

import jomung.Item;
import jomung.Room;
import jomung.Weapon;
import jomung.enums.ItemType;
import jomung.movingobject.Enemy;
import jomung.movingobject.Player;

public class bigArrow extends Item implements Weapon {

	private static final long serialVersionUID = 7700257546447957261L;

	public bigArrow() {
		super.setItemType(ItemType.bigArrow);
	}

	@Override
	public void useItem(Player player, Room playerRoom, int index) {
		List<Enemy> enemies = playerRoom.getEnemiesList();
		List<Player> players = playerRoom.getPlayersList();
		player.increaseHealth(enemies.size() * 10);
		while (enemies.size() != 0) {
			enemies.get(0).costHealth();
		}
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).equals(player)) {
				players.get(i).costHealth();
			}
		}
	}

	@Override
	public void useItem(Player player, Room playerRoom) {
	}

	@Override
	public String toString() {
		return "bigArrow";
	}

}
