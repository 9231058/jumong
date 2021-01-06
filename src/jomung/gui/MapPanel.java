package jomung.gui;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import jomung.movingobject.Player;
import jomung.view.RoomView;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = -7102103171371189068L;
	private SpringLayout sl_contentPane;
	private Player player;
	private JComponent[][] components;

	public MapPanel() {
		sl_contentPane = new SpringLayout();
		setLayout(sl_contentPane);
		setPreferredSize(new Dimension(500, 500));

		player = MainFrame.getInstance().getPlayer();

		int X = player.getMap().getMAP_MAX_X();
		int Y = player.getMap().getMAP_MAX_Y();
		components = new JComponent[X][Y];
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				components[i][j] = RoomView
						.view(player.getMap().getRooms()[i][j]);
			}
		}
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				sl_contentPane.putConstraint(SpringLayout.NORTH,
						components[i][j], 50 * j, SpringLayout.NORTH, this);
				sl_contentPane.putConstraint(SpringLayout.WEST,
						components[i][j], 50 * i, SpringLayout.WEST, this);
				add(components[i][j]);
			}
		}
	}

}
