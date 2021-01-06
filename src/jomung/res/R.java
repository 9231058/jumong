package jomung.res;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import jomung.ItemPutter;
import jomung.Room;
import jomung.database.DataBase;
import jomung.movingobject.Enemy;
import jomung.movingobject.Player;

public class R {
	private static R instance = null;

	private ArrayList<Point> startPoints;
	private Player[] players;
	private Room[][] rooms;
	private Point endPoint;
	private int x;
	private int y;

	public static R getInstance() {
		if (instance == null) {
			instance = new R();
		}
		return instance;
	}

	private R() {
		startPoints = new ArrayList<>();
	}

	public void init() {
		DataBase.getInstance().start();
		System.out.println("Start ...");
		for (int x = 0; x < this.x; x++) {
			for (int y = 0; y < this.y; y++) {
				ItemPutter.putRandomItem(rooms[x][y]);
				Random rand = new Random();
				double distance = Math.pow(endPoint.getX() - x, 2)
						+ Math.pow(endPoint.getY() - y, 2);
				if (!rooms[x][y].isWall() && distance != 0) {
					rooms[x][y].setScore((int) ((1 / distance) * rand
							.nextInt(1000000)));
				}
				if (!rooms[x][y].isWall()) {
					for (int i = 0; i < rand.nextInt(4); i++) {
						rooms[x][y].addMovingObject(new Enemy(x, y));
					}
				}
			}
		}
		players = new Player[startPoints.size()];
		for (int i = 0; i < startPoints.size(); i++) {
			players[i] = new Player(startPoints.get(i).x, startPoints.get(i).y);
		}
		DataBase.getInstance().setInit(true);
	}

	public void setSize(int x, int y) {
		rooms = new Room[x][y];
		this.x = x;
		this.y = y;
	}

	public void addPlayer(int x, int y) {
		startPoints.add(new Point(x, y));
	}

	public void addRoom(int x, int y, Room room) {
		rooms[x][y] = room;
	}

	public void setEndPoint(Point point) {
		endPoint = point;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public Room[][] getRooms() {
		return rooms;
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
