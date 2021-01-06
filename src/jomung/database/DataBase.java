package jomung.database;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import jomung.Room;
import jomung.movingobject.Player;
import jomung.res.R;

public class DataBase extends Thread implements Serializable {

	private static final long serialVersionUID = -152409260481540286L;
	private static DataBase instance = null;
	private transient ServerSocket serverSocketRequest;
	private transient ServerSocket serverSocketData;
	private Room[][] rooms;
	private Player[] players;
	private Point endPoint;
	private int X;
	private int Y;
	private boolean init = false;

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	public static void setInstance(DataBase newInstance) {
		instance = newInstance;
	}

	private DataBase() {
		super("DataBase Thread");
		rooms = R.getInstance().getRooms();
		X = R.getInstance().getX();
		Y = R.getInstance().getY();
		endPoint = R.getInstance().getEndPoint();
	}

	public Room[][] getRooms() {
		return rooms;
	}

	public Player[] getPlayers() {
		return players;
	}

	@Override
	public void run() {
		try {
			serverSocketRequest = new ServerSocket(13731);
			serverSocketData = new ServerSocket(13732);
			while (true) {
				Socket socketRequest = serverSocketRequest.accept();
				Socket socketData = serverSocketData.accept();
				reply(socketRequest, socketData);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	private void reply(Socket socketRequest, Socket socketData) {
		try {
			BufferedReader istreamRequest = new BufferedReader(
					new InputStreamReader(socketRequest.getInputStream()));
			PrintWriter ostreamRequest = new PrintWriter(
					socketRequest.getOutputStream());
			ObjectOutputStream ostreamData = new ObjectOutputStream(
					socketData.getOutputStream());
			ostreamData.flush();
			ObjectInputStream istreamData = new ObjectInputStream(
					socketData.getInputStream());
			String request = istreamRequest.readLine();
			String[] splitedRequest = request.split(" ");
			switch (splitedRequest[0]) {
			case "GET":
				ostreamData.writeObject(get(
						Integer.parseInt(splitedRequest[1]),
						Integer.parseInt(splitedRequest[2])));
				break;
			case "PUT":
				put(Integer.parseInt(splitedRequest[1]),
						Integer.parseInt(splitedRequest[2]),
						(Room) istreamData.readObject());
				break;
			case "SIZE":
				ostreamRequest.println(X + " " + Y);
				ostreamRequest.flush();
				break;
			case "END":
				ostreamRequest.println(endPoint.x + " " + endPoint.y);
				ostreamRequest.flush();
				break;
			}
			ostreamData.flush();
			socketData.close();
			socketRequest.close();
		} catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	private Room get(int x, int y) {
		return rooms[x][y];
	}

	private void put(int x, int y, Room room) {
		rooms[x][y] = room;
		if (init == false)
			return;
		for (int i = 0; i < room.getPlayersList().size(); i++) {
			for (int j = 0; j < players.length; j++) {
				if (players[j].getId().equals(room.getPlayersList().get(i))) {
					players[j] = room.getPlayersList().get(i);
				}
			}
		}
		for (int i = 0; i < room.getEnemiesList().size(); i++) {
			room.getEnemiesList().get(i).isAlive();
		}
	}

	public void setInit(boolean init) {
		this.init = init;
		players = R.getInstance().getPlayers();
	}

}
