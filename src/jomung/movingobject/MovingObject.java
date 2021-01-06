package jomung.movingobject;

import java.awt.Point;
import java.io.Serializable;
import java.util.UUID;

import jomung.Map;
import jomung.Room;
import jomung.enums.Direction;

public abstract class MovingObject implements Serializable {

	private static final long serialVersionUID = 6714552529815204137L;
	protected Point currentLocation;
	protected Room room;
	protected int health = 100;
	protected final UUID id;

	public MovingObject(int x, int y) {
		id = UUID.randomUUID();
		currentLocation = new Point(x, y);
	}

	public void ready() {
		room = Map.getInstance().getRoomAt(getCurrentLocationX(),
				getCurrentLocationY());
		for (int i = 0; i < room.getPlayersList().size(); i++) {
			if (this.equals(room.getPlayersList().get(i))) {
				health = room.getPlayersList().get(i).health;
				return;
			}
		}
		for (int i = 0; i < room.getEnemiesList().size(); i++) {
			if (this.equals(room.getEnemiesList().get(i))) {
				health = room.getEnemiesList().get(i).health;
				return;
			}
		}
		health = 0;

	}

	public UUID getId() {
		return id;
	}

	public int getCurrentLocationX() {
		return currentLocation.x;
	}

	public int getCurrentLocationY() {
		return currentLocation.y;
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocationSeeAble() {
		Map.getInstance().setRoomSeeable(currentLocation.x, currentLocation.y,
				room);
	}

	public boolean isAlive() {
		if (health > 0) {
			return true;
		}
		room.removeMovingObject(this);
		update();
		return false;
	}

	public void move(Direction direction) {
		Point newPoint = new Point(currentLocation.x + direction.x,
				currentLocation.y + direction.y);
		room = Map.getInstance().moveObject(currentLocation, newPoint, this);
		currentLocation = newPoint;
	}

	public void increaseHealth(int amount) {
		health += amount;
		if (health > 100) {
			health -= (health % 100);
		}
		update();
	}

	public int getHealth() {
		return health;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MovingObject)) {
			return false;
		}
		MovingObject movingObject = (MovingObject) object;
		if (movingObject.id.compareTo(id) == 0) {
			return true;
		}
		return false;
	}

	protected void save() {
		Map.getInstance().moveObject(currentLocation, currentLocation, this);
	}

	protected void setLocation(int x, int y) {
		room = Map.getInstance().moveObject(currentLocation, new Point(x, y),
				this);
		currentLocation.setLocation(x, y);
	}

	protected void update() {
		if (room.getPlayersList().contains(this)
				|| room.getEnemiesList().contains(this)) {
			room.removeMovingObject(this);
			room.addMovingObject(this);
		}
		Map.getInstance().setRoomAt(currentLocation.x, currentLocation.y, room);
	}

	abstract public void costHealth();
}
