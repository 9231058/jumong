package jomung.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import jomung.Item;

public class ItemComponent extends JComponent {

	private static final long serialVersionUID = -6518926081174925603L;
	private ImageIcon image;
	private String itemName;

	public ItemComponent(Item item) {
		itemName = item.getString();
		setPreferredSize(new Dimension(50, 50));
		image = item.getImage();
		setToolTipText(item.toString());
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.drawImage(image.getImage(), 0, 0, image.getImageObserver());
	}

	@Override
	public String toString() {
		return itemName;
	}
}
