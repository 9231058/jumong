package jomung.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class EnemyComponent extends JComponent {

	private static final long serialVersionUID = -5834664776311242478L;

	private ImageIcon image;

	public EnemyComponent(ImageIcon image) {
		setPreferredSize(new Dimension(50, 50));
		this.image = image;
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphics.drawImage(image.getImage(), 0, 0, image.getImageObserver());
	}

}
