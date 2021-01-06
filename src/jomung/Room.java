package jomung;

import java.io.Serializable;
import java.util.ArrayList;

import jomung.items.Chest;
import jomung.movingobject.Enemy;
import jomung.movingobject.MovingObject;
import jomung.movingobject.Player;

public class Room implements Serializable {

	private static final long serialVersionUID = -871281491634538152L;
	private boolean wall;
	private int score;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Item> items = new ArrayList<>();
	private ArrayList<Chest> chests = new ArrayList<>();

	public void removeMovingObject(MovingObject object) {
		if (object instanceof Player) {
			players.remove((Player) object);
		} else if (object instanceof Enemy) {
			enemies.remove((Enemy) object);
		}
	}

	public void addMovingObject(MovingObject object) {
		if (object instanceof Player) {
			players.add((Player) object);
		} else if (object instanceof Enemy) {
			enemies.add((Enemy) object);
		}
	}

	public int getScore() {
		int score = this.score;
		this.score = 0;
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Item> getItemsList() {
		return items;
	}

	public ArrayList<Chest> getChestsList() {
		return chests;
	}

	public ArrayList<Enemy> getEnemiesList() {
		return enemies;
	}

	public ArrayList<Player> getPlayersList() {
		return players;
	}

	public boolean isWall() {
		return wall;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}

	public void addItem(Item item) {
		if (item instanceof Chest) {
			chests.add((Chest) item);
		}
		items.add(item);
	}

}
