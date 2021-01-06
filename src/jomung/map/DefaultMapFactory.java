package jomung.map;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import jomung.Room;
import jomung.res.R;

public class DefaultMapFactory implements MapFactory {

	private Scanner cin;
	private Room[][] rooms = null;
	private ArrayList<Point> startPoints = new ArrayList<>();
	private Point endPoint = null;
	private int x;
	private int y;
	private String path;

	public DefaultMapFactory() {
		try {
			cin = new Scanner(new File(path));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void create() throws IllegalArgumentException {
		x = Integer.parseInt(cin.next());
		y = Integer.parseInt(cin.next());
		rooms = new Room[x][y];
		for (int i = 0; i < x; i++) {
			String row = cin.next("[.|SF]+");
			for (int j = 0; j < y; j++) {
				char roomChar = row.charAt(j);
				if (roomChar == '.') {
					rooms[i][j] = new Room();
					rooms[i][j].setWall(false);
				} else if (roomChar == '|') {
					rooms[i][j] = new Room();
					rooms[i][j].setWall(true);
				} else if (roomChar == 'S') {
					rooms[i][j] = new Room();
					rooms[i][j].setWall(false);
					startPoints.add(new Point(i, j));
				} else if (roomChar == 'F') {
					rooms[i][j] = new Room();
					rooms[i][j].setWall(false);
					endPoint = new Point(i, j);
				}
			}
		}
		if (startPoints.size() == 0 || endPoint == null) {
			throw (new IllegalArgumentException());
		}
	}

	@Override
	public void setR() {
		R.getInstance().setSize(x, y);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				R.getInstance().addRoom(i, j, rooms[i][j]);
			}
		}
		for (int i = 0; i < startPoints.size(); i++) {
			R.getInstance().addPlayer(startPoints.get(i).x,
					startPoints.get(i).y);
		}
		R.getInstance().setEndPoint(endPoint);
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}
}
