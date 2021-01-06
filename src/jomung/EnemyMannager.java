package jomung;

import java.awt.Point;
import java.util.ArrayList;

import jomung.bfs.BFS;
import jomung.database.DataBase;
import jomung.enums.Direction;
import jomung.movingobject.Enemy;
import jomung.movingobject.Player;
import jomung.res.R;

public class EnemyMannager extends Thread {

	private static EnemyMannager instance;
	private ArrayList<Enemy> enemies;

	public static EnemyMannager getInstance() {
		if (instance == null) {
			instance = new EnemyMannager();
		}
		return instance;
	}

	private EnemyMannager() {
		enemies = new ArrayList<>();
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).ready();
				if (enemies.get(i).isAlive()) {
					moveEnemiesToPlayer(enemies.get(i));
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	private void moveEnemiesToPlayer(Enemy enemy) {
		int x = enemy.getCurrentLocationX();
		int y = enemy.getCurrentLocationY();
		Direction direction = null;
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
		} catch (IllegalArgumentException exception) {

		}
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}
}
