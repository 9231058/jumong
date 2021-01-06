package jomung;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import jomung.bfs.BFS;
import jomung.database.DataBase;
import jomung.enums.Direction;
import jomung.movingobject.Enemy;
import jomung.movingobject.Player;
import jomung.res.R;

public class EnemyMannager implements Serializable {

	private static final long serialVersionUID = -6508787029291290188L;
	private static EnemyMannager instance;
	private ArrayList<Enemy> enemies;
	private int index = 0;

	public static EnemyMannager getInstance() {
		if (instance == null) {
			instance = new EnemyMannager();
		}
		return instance;
	}

	public static void setInstance(EnemyMannager newInstance) {
		instance = newInstance;
	}

	private EnemyMannager() {
		enemies = new ArrayList<>();
		BFS.MAX_X = R.getInstance().getX();
		BFS.MAX_Y = R.getInstance().getY();
	}

	public void run() {
		enemies.get(index).ready();
		if (enemies.get(index).isAlive()) {
			moveEnemiesToPlayer(enemies.get(index));
		}
		index = (index + 1) % enemies.size();
	}

	private void moveEnemiesToPlayer(Enemy enemy) {
		int x = enemy.getCurrentLocationX();
		int y = enemy.getCurrentLocationY();
		Direction direction = Direction.UP;
		int distance = Integer.MAX_VALUE;
		Player[] players = DataBase.getInstance().getPlayers();
		for (int i = 0; i < players.length; i++) {
			if (x + 1 < R.getInstance().getX()) {
				if (!DataBase.getInstance().getRooms()[x + 1][y].isWall()
						&& DataBase.getInstance().getRooms()[x + 1][y]
								.getEnemiesList().size() < 3) {
					if (distance > BFS.doBFS(new Point(x + 1, y),
							players[i].getCurrentLocation())) {
						distance = BFS.doBFS(new Point(x + 1, y),
								players[i].getCurrentLocation());
						direction = Direction.UP;
					}
				}
			}
			if (x - 1 >= 0) {
				if (!DataBase.getInstance().getRooms()[x - 1][y].isWall()
						&& DataBase.getInstance().getRooms()[x - 1][y]
								.getEnemiesList().size() < 3) {
					if (distance > BFS.doBFS(new Point(x - 1, y),
							players[i].getCurrentLocation())) {
						distance = BFS.doBFS(new Point(x - 1, y),
								players[i].getCurrentLocation());
						direction = Direction.DOWN;
					}
				}
			}
			if (y + 1 < R.getInstance().getY()) {
				if (!DataBase.getInstance().getRooms()[x][y + 1].isWall()
						&& DataBase.getInstance().getRooms()[x][y + 1]
								.getEnemiesList().size() < 3) {
					if (distance > BFS.doBFS(new Point(x, y + 1),
							players[i].getCurrentLocation())) {
						distance = BFS.doBFS(new Point(x, y + 1),
								players[i].getCurrentLocation());
						direction = Direction.RIGHT;
					}
				}
			}
			if (y - 1 >= 0) {
				if (!DataBase.getInstance().getRooms()[x][y - 1].isWall()
						&& DataBase.getInstance().getRooms()[x][y - 1]
								.getEnemiesList().size() < 3) {
					if (distance > BFS.doBFS(new Point(x, y - 1),
							players[i].getCurrentLocation())) {
						distance = BFS.doBFS(new Point(x, y - 1),
								players[i].getCurrentLocation());
						direction = Direction.LEFT;

					}
				}
			}
		}
		try {
			enemy.move(direction);
		} catch (IllegalArgumentException | NullPointerException exception) {
		}
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}
}
