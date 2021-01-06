package jomung;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import jomung.movingobject.MovingObject;

public class Map implements Serializable {
	private static final long serialVersionUID = 3655351311889103698L;
	private String IP = "127.0.0.1";
	private Point endPoint = null;
	private int MAP_MAX_X;
	private int MAP_MAX_Y;
	private Room[][] rooms;

	public void setIP(String IP) {
		this.IP = IP;
	}

	@SuppressWarnings("unused")
	public Map() {
		try {
			Socket socketRequest = new Socket(IP, 13731);
			Socket socketData = new Socket(IP, 13732);
			socketData.setSoTimeout(1000);
			socketRequest.setSoTimeout(1000);
			BufferedReader istreamRequest = new BufferedReader(
					new InputStreamReader(socketRequest.getInputStream()));
			PrintWriter ostreamRequest = new PrintWriter(
					socketRequest.getOutputStream());
			ObjectOutputStream ostreamData = new ObjectOutputStream(
					socketData.getOutputStream());
			ostreamData.flush();
			ObjectInputStream istreamData = new ObjectInputStream(
					socketData.getInputStream());
			ostreamRequest.println("SIZE");
			ostreamRequest.flush();
			String response = istreamRequest.readLine();
			String[] splitedResponse = response.split(" ");
			MAP_MAX_X = Integer.parseInt(splitedResponse[0]);
			MAP_MAX_Y = Integer.parseInt(splitedResponse[1]);
			socketData.close();
			socketRequest.close();
		} catch (IOException exception) {
			exception.printStackTrace();
			MAP_MAX_X = 0;
			MAP_MAX_Y = 0;
		}
		rooms = new Room[MAP_MAX_X][MAP_MAX_Y];
	}

	public Room moveObject(Point oldPoint, Point newPoint, MovingObject object) {
		Room oldRoom = getRoomAt(oldPoint.x, oldPoint.y);
		Room newRoom = getRoomAt(newPoint.x, newPoint.y);
		if (newRoom.isWall()) {
			setRoomSeeable(newPoint.x, newPoint.y, newRoom);
			throw new IllegalArgumentException("There is wall ....");
		}
		oldRoom.removeMovingObject(object);
		setRoomAt(oldPoint.x, oldPoint.y, oldRoom);
		newRoom.addMovingObject(object);
		setRoomAt(newPoint.x, newPoint.y, newRoom);
		setRoomSeeable(oldPoint.x, oldPoint.y, oldRoom);
		setRoomSeeable(newPoint.x, newPoint.y, newRoom);
		return newRoom;
	}

	public Room getRoomAt(int x, int y) throws IllegalArgumentException {
		if (x < 0 || x >= MAP_MAX_X) {
			throw new IllegalArgumentException("Invalid x");
		}
		if (y < 0 || y >= MAP_MAX_Y) {
			throw new IllegalArgumentException("Invalid y");
		}
		Room room = null;
		try {
			Socket socketRequest = new Socket(IP, 13731);
			Socket socketData = new Socket(IP, 13732);
			PrintWriter ostreamRequest = new PrintWriter(
					socketRequest.getOutputStream());
			ObjectOutputStream ostreamData = new ObjectOutputStream(
					socketData.getOutputStream());
			ostreamData.flush();
			ObjectInputStream istreamData = new ObjectInputStream(
					socketData.getInputStream());
			ostreamRequest.println("GET " + x + " " + y);
			ostreamRequest.flush();
			room = (Room) istreamData.readObject();
			socketData.close();
			socketRequest.close();
		} catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		return room;
	}

	@SuppressWarnings("unused")
	public void setRoomAt(int x, int y, Room room)
			throws IllegalArgumentException {
		if (x < 0 || x >= MAP_MAX_X) {
			throw new IllegalArgumentException("Invalid x");
		}
		if (y < 0 || y >= MAP_MAX_Y) {
			throw new IllegalArgumentException("Invalid y");
		}
		try {
			Socket socketRequest = new Socket(IP, 13731);
			Socket socketData = new Socket(IP, 13732);
			PrintWriter ostreamRequest = new PrintWriter(
					socketRequest.getOutputStream());
			ObjectOutputStream ostreamData = new ObjectOutputStream(
					socketData.getOutputStream());
			ObjectInputStream istreamData = new ObjectInputStream(
					socketData.getInputStream());
			ostreamData.flush();
			ostreamRequest.println("PUT " + x + " " + y);
			ostreamRequest.flush();
			ostreamData.writeObject(room);
			ostreamData.flush();
			socketData.close();
			socketRequest.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public Point getEndPoint() {
		if (endPoint == null) {
			try {
				endPoint = new Point();
				Socket socketRequest = new Socket(IP, 13731);
				Socket socketData = new Socket(IP, 13732);
				socketData.setSoTimeout(1000);
				socketRequest.setSoTimeout(1000);
				BufferedReader istreamRequest = new BufferedReader(
						new InputStreamReader(socketRequest.getInputStream()));
				PrintWriter ostreamRequest = new PrintWriter(
						socketRequest.getOutputStream());
				ObjectOutputStream ostreamData = new ObjectOutputStream(
						socketData.getOutputStream());
				ostreamData.flush();
				ObjectInputStream istreamData = new ObjectInputStream(
						socketData.getInputStream());
				ostreamRequest.println("END");
				ostreamRequest.flush();
				String response = istreamRequest.readLine();
				String[] splitedResponse = response.split(" ");
				endPoint.x = Integer.parseInt(splitedResponse[0]);
				endPoint.y = Integer.parseInt(splitedResponse[1]);
				socketData.close();
				socketRequest.close();
			} catch (IOException exception) {
				endPoint = null;
			}
		}
		return endPoint;
	}

	public void setRoomSeeable(int x, int y, Room room) {
		rooms[x][y] = room;
	}

	public int getMAP_MAX_X() {
		return MAP_MAX_X;
	}

	public int getMAP_MAX_Y() {
		return MAP_MAX_Y;
	}

	public Room[][] getRooms() {
		return rooms;
	}
}
