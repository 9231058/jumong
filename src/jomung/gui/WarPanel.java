package jomung.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import jomung.Room;
import jomung.view.EnemiesView;

public class WarPanel extends JPanel {

	private static final long serialVersionUID = -1151904502732427380L;
	private SpringLayout sl_contentPane;
	private JComponent[] components;
	private BattleDialog dialog;

	public WarPanel(Room room, BattleDialog dialog) {
		this.dialog = dialog;

		sl_contentPane = new SpringLayout();
		setPreferredSize(new Dimension(500, 600));
		setLayout(sl_contentPane);
		setBackground(Color.GREEN);

		components = EnemiesView.view(room.getEnemiesList());
		for (int i = 0; i < components.length; i++) {
			sl_contentPane.putConstraint(SpringLayout.NORTH, components[i], 0,
					SpringLayout.NORTH, this);
			sl_contentPane.putConstraint(SpringLayout.WEST, components[i],
					i * 50, SpringLayout.WEST, this);
			components[i].addMouseListener(new WeaponUser(i));
			add(components[i]);
		}
	}

	private class WeaponUser implements MouseListener {

		private int index;

		public WeaponUser(int index) {
			this.index = index;
		}

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
			String weaponName = dialog.getWeapon();
			if (weaponName == null) {
				return;
			}
			MainFrame.getInstance().killEnemy(weaponName, index);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

	}
}
