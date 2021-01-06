package jomung.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import jomung.Map;
import jomung.Room;

public class BattleDialog extends JDialog {

	private static final long serialVersionUID = 2392121510482701931L;
	private WarPanel warPanel;
	private WeaponPanel weaponPanel;
	private SpringLayout sl_contentPane;
	private int x;
	private int y;
	private String weaponName;

	public BattleDialog() {
		super(MainFrame.getInstance(), "Battle");
		setSize(new Dimension(500, 800));
		sl_contentPane = new SpringLayout();
		setLayout(sl_contentPane);
		addWindowListener(new Ender());

		x = MainFrame.getInstance().getPlayer().getCurrentLocationX();
		y = MainFrame.getInstance().getPlayer().getCurrentLocationY();

		warPanel = new WarPanel(Map.getInstance().getRooms()[x][y], this);
		sl_contentPane.putConstraint(SpringLayout.NORTH, warPanel, 0,
				SpringLayout.NORTH, this);
		sl_contentPane.putConstraint(SpringLayout.WEST, warPanel, 0,
				SpringLayout.WEST, this);
		add(warPanel);

		weaponPanel = new WeaponPanel(this);
		sl_contentPane.putConstraint(SpringLayout.NORTH, weaponPanel, 0,
				SpringLayout.SOUTH, warPanel);
		sl_contentPane.putConstraint(SpringLayout.WEST, weaponPanel, 0,
				SpringLayout.WEST, this);
		add(weaponPanel);

		new Refresh();
	}

	public void setWeapon(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getWeapon() {
		String temp = weaponName;
		weaponName = null;
		return temp;
	}

	private class Refresh implements ActionListener {

		private Timer timer;

		public Refresh() {
			timer = new Timer(100, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			BattleDialog.this.remove(warPanel);
			warPanel = new WarPanel(Map.getInstance().getRooms()[x][y],
					BattleDialog.this);
			sl_contentPane.putConstraint(SpringLayout.NORTH, warPanel, 0,
					SpringLayout.NORTH, BattleDialog.this);
			sl_contentPane.putConstraint(SpringLayout.WEST, warPanel, 0,
					SpringLayout.WEST, BattleDialog.this);
			BattleDialog.this.add(warPanel);

			BattleDialog.this.remove(weaponPanel);
			weaponPanel = new WeaponPanel(BattleDialog.this);
			sl_contentPane.putConstraint(SpringLayout.NORTH, weaponPanel, 0,
					SpringLayout.SOUTH, warPanel);
			sl_contentPane.putConstraint(SpringLayout.WEST, weaponPanel, 0,
					SpringLayout.WEST, BattleDialog.this);
			BattleDialog.this.add(weaponPanel);

			SwingUtilities.updateComponentTreeUI(BattleDialog.this);
			timer.restart();
		}

	}

	private class Ender implements WindowListener {

		@Override
		public void windowActivated(WindowEvent event) {
		}

		@Override
		public void windowClosed(WindowEvent event) {
		}

		@Override
		public void windowClosing(WindowEvent event) {
			MainFrame.getInstance().getPlayer().autoWar();
			Room room = Map.getInstance().getRoomAt(x, y);
			for (int i = 0; i < room.getEnemiesList().size(); i++) {
				MainFrame.getInstance().getPlayer().costHealth();
			}
			MainFrame.getInstance().endWar();
		}

		@Override
		public void windowDeactivated(WindowEvent event) {
		}

		@Override
		public void windowDeiconified(WindowEvent event) {
		}

		@Override
		public void windowIconified(WindowEvent event) {
		}

		@Override
		public void windowOpened(WindowEvent event) {
		}

	}

}
