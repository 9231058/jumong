package jomung.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import jomung.Room;
import jomung.gui.RoomDialog;

public class MapComponent extends JComponent {

	private static final long serialVersionUID = 1922482275040651370L;
	private ImageIcon image;
	private Room room;

	public MapComponent(ImageIcon image) {
		setPreferredSize(new Dimension(50, 50));
		this.image = image;
	}

	public MapComponent(ImageIcon image, Room room) {
		this(image);
		this.room = room;
		this.addMouseListener(new roomDialog());
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.drawImage(image.getImage(), 0, 0, image.getImageObserver());
	}

	private class roomDialog implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent event) {
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}

		@Override
		public void mousePressed(MouseEvent event) {
			new RoomDialog(MapComponent.this.room).setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

	}
}
