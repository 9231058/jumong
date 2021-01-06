package jomung.view;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import jomung.Room;
import jomung.gui.MainFrame;

public class RoomView {
	private RoomView() {
	}

	public static JComponent view(Room room) {
		JComponent component = null;
		if (room == null) {
			component = new MapComponent(new ImageIcon("icons/nullIcon.jpg"));
		} else if (room.getPlayersList().contains(
				MainFrame.getInstance().getPlayer())) {
			component = new MapComponent(new ImageIcon("icons/tuxIcon.png"),
					room);
		} else if (room.isWall()) {
			component = new MapComponent(new ImageIcon("icons/wallIcon.png"));
		} else {
			component = new MapComponent(new ImageIcon("icons/houseIcon.png"),
					room);
		}
		return component;
	}
}
