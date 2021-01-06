package jomung.movingobject;

import jomung.EnemyMannager;
import jomung.Map;
import jomung.Room;

public class Enemy extends MovingObject {

	private static final long serialVersionUID = -4213944479311768563L;

	public Enemy(int x, int y) {
		super(x, y);
		Room room = Map.getInstance().getRoomAt(x, y);
		room.addMovingObject(this);
		this.room = room;
		Map.getInstance().setRoomAt(x, y, room);
		EnemyMannager.getInstance().addEnemy(this);
	}

	@Override
	public boolean isAlive() {
		if (!super.isAlive()) {
			EnemyMannager.getInstance().removeEnemy(this);
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Enemy";
	}

	@Override
	public void costHealth() {
		room.getEnemiesList().remove(this);
		health = 0;
		update();
	}

}
