package jomung.bfs;

import java.awt.Point;
import java.util.ArrayList;

import jomung.database.DataBase;

public final class BFS {

	private static int INF = Integer.MAX_VALUE;
	public static int MAX_X;
	public static int MAX_Y;

	public static int doBFS(Point startPoint, Point endPoint) {
		int colors[][] = new int[MAX_X][MAX_Y];
		int distance[][] = new int[MAX_X][MAX_Y];
		for (int i = 0; i < MAX_X; i++) {
			for (int j = 0; j < MAX_Y; j++) {
				colors[i][j] = 0;
				distance[i][j] = INF;
			}
		}
		try {
			distance[startPoint.x][startPoint.y] = 0;
			colors[startPoint.x][startPoint.y] = 1;
		} catch (ArrayIndexOutOfBoundsException exception) {
			return INF;
		}
		ArrayList<Point> Q = new ArrayList<>();
		Q.add(startPoint);
		while (!Q.isEmpty()) {
			try {
				Point tempPoint = Q.remove(0);
				if (!DataBase.getInstance().getRooms()[tempPoint.x + 1][tempPoint.y]
						.isWall()) {
					if (DataBase.getInstance().getRooms()[tempPoint.x + 1][tempPoint.y]
							.getEnemiesList().size() < 3) {
						if (colors[tempPoint.x + 1][tempPoint.y] == 0) {
							colors[tempPoint.x + 1][tempPoint.y] = 1;
							distance[tempPoint.x + 1][tempPoint.y] = distance[tempPoint.x][tempPoint.y] + 1;
							Q.add(new Point(tempPoint.x + 1, tempPoint.y));
						}
					}
				}
				if (!DataBase.getInstance().getRooms()[tempPoint.x - 1][tempPoint.y]
						.isWall()) {
					if (DataBase.getInstance().getRooms()[tempPoint.x - 1][tempPoint.y]
							.getEnemiesList().size() < 3) {
						if (colors[tempPoint.x - 1][tempPoint.y] == 0) {
							colors[tempPoint.x - 1][tempPoint.y] = 1;
							distance[tempPoint.x - 1][tempPoint.y] = distance[tempPoint.x][tempPoint.y] + 1;
							Q.add(new Point(tempPoint.x - 1, tempPoint.y));
						}
					}
				}
				if (!DataBase.getInstance().getRooms()[tempPoint.x][tempPoint.y + 1]
						.isWall()) {
					if (DataBase.getInstance().getRooms()[tempPoint.x][tempPoint.y + 1]
							.getEnemiesList().size() < 3) {
						if (colors[tempPoint.x][tempPoint.y + 1] == 0) {
							colors[tempPoint.x][tempPoint.y + 1] = 1;
							distance[tempPoint.x][tempPoint.y + 1] = distance[tempPoint.x][tempPoint.y] + 1;
							Q.add(new Point(tempPoint.x, tempPoint.y + 1));
						}
					}
				}
				if (!DataBase.getInstance().getRooms()[tempPoint.x][tempPoint.y - 1]
						.isWall()) {
					if (DataBase.getInstance().getRooms()[tempPoint.x][tempPoint.y - 1]
							.getEnemiesList().size() < 3) {
						if (colors[tempPoint.x][tempPoint.y - 1] == 0) {
							colors[tempPoint.x][tempPoint.y - 1] = 1;
							distance[tempPoint.x][tempPoint.y - 1] = distance[tempPoint.x][tempPoint.y] + 1;
							Q.add(new Point(tempPoint.x, tempPoint.y - 1));
						}
					}
				}
				colors[tempPoint.x][tempPoint.y] = 2;
			} catch (ArrayIndexOutOfBoundsException exception) {
				continue;
			}
		}
		return distance[endPoint.x][endPoint.y];
	}
}
