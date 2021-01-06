package jomung.gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import jomung.view.WeaponsView;

public class WeaponPanel extends JPanel {

	private static final long serialVersionUID = -8506703734372689839L;
	private SpringLayout sl_contentPane;
	private JComponent[] weapons;
	private BattleDialog dialog;

	public WeaponPanel(BattleDialog dialog) {
		this.dialog = dialog;

		setPreferredSize(new Dimension(500, 200));
		sl_contentPane = new SpringLayout();
		setLayout(sl_contentPane);

		int row = 0;

		weapons = WeaponsView.view(MainFrame.getInstance().getPlayer()
				.getItemsList());
		int index = 0;
		for (; row < weapons.length / 8 + 1; row++) {
			for (int i = 1; i <= 8 && index < weapons.length; index++, i++) {
				sl_contentPane.putConstraint(SpringLayout.NORTH,
						weapons[index], row * 50, SpringLayout.NORTH, this);
				sl_contentPane.putConstraint(SpringLayout.WEST, weapons[index],
						50 * i, SpringLayout.WEST, this);
				weapons[index].addMouseListener(new WeaponSelecter(
						weapons[index]));
				add(weapons[index]);
			}
		}
	}

	private class WeaponSelecter implements MouseListener {
		String weaponName;

		public WeaponSelecter(JComponent component) {
			weaponName = component.toString();
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
			dialog.setWeapon(weaponName);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

	}
}
