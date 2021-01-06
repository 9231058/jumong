package jomung.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import jomung.GameMannager;
import jomung.enums.Command;
import jomung.movingobject.Player;
import jomung.ui.PlayerUI2D;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -621777937389967567L;
	private static MainFrame instance = null;
	private JPanel contentPane;
	private SpringLayout sl_contentPane;
	private JButton saveButton;
	private MapPanel mapPanel;
	private InventoryPanel inventoryPanel;
	private StatusPanel statusPanel;
	private PlayerUI2D ui;
	private MovmentKey key;

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	private MainFrame() {
		super("J O M U N G");

		key = new MovmentKey();
		contentPane = new JPanel();
		sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.addKeyListener(key);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, saveButton, -50,
				SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, saveButton, 10,
				SpringLayout.WEST, contentPane);
		saveButton.addActionListener(new Save());
		saveButton.setFocusable(false);
		contentPane.add(saveButton);

		setContentPane(contentPane);
		new Refresh();
	}

	public void setUI(PlayerUI2D ui) {
		this.ui = ui;

		saveButton.setEnabled(!ui.isClient());

		mapPanel = new MapPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, mapPanel, 0,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, mapPanel, 10,
				SpringLayout.WEST, contentPane);
		contentPane.add(mapPanel);

		inventoryPanel = new InventoryPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, inventoryPanel, 0,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, inventoryPanel, -10,
				SpringLayout.EAST, contentPane);
		contentPane.add(inventoryPanel);

		statusPanel = new StatusPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, statusPanel, 0,
				SpringLayout.SOUTH, inventoryPanel);
		sl_contentPane.putConstraint(SpringLayout.EAST, statusPanel, -10,
				SpringLayout.EAST, contentPane);
		contentPane.add(statusPanel);
	}

	public Player getPlayer() {
		return ui.getPlayer();
	}

	public void getItem(String itemName) {
		ui.update(Command.GET_ITEM, itemName);
	}

	public void useItem(String itemName) {
		ui.update(Command.USE_ITEM, itemName);
	}

	public void dropItem(String itemName) {
		ui.update(Command.DROP_ITEM, itemName);
	}

	public void shovel(int x, int y) {
		ui.update(Command.SHOVEL, x, y);
	}

	public void endWar() {
		ui.updateWar(Command.END_WAR);
	}

	public void killEnemy(String weaponName, int index) {
		ui.updateWar(Command.KILL_ENEMY, weaponName, index);
	}

	private class MovmentKey implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				ui.update(Command.MOVE_LEFT);
			} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				ui.update(Command.MOVE_RIGHT);
			} else if (event.getKeyCode() == KeyEvent.VK_UP) {
				ui.update(Command.MOVE_UP);
			} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				ui.update(Command.MOVE_DOWN);
			}
		}

		@Override
		public void keyReleased(KeyEvent event) {
		}

		@Override
		public void keyTyped(KeyEvent event) {
		}

	}

	private class Save implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Load J O M U N G");
			if (chooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
				GameMannager.getInstance().save(
						chooser.getSelectedFile().getPath());
			}
		}
	}

	private class Refresh implements ActionListener {

		Timer timer;

		public Refresh() {
			timer = new Timer(100, this);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			if (ui != null) {
				if (mapPanel != null) {
					contentPane.remove(mapPanel);
					mapPanel = new MapPanel();
					sl_contentPane.putConstraint(SpringLayout.NORTH, mapPanel,
							0, SpringLayout.NORTH, contentPane);
					sl_contentPane.putConstraint(SpringLayout.WEST, mapPanel,
							10, SpringLayout.WEST, contentPane);
					contentPane.add(mapPanel);
				}
				if (inventoryPanel != null) {
					contentPane.remove(inventoryPanel);
					inventoryPanel = new InventoryPanel();
					sl_contentPane.putConstraint(SpringLayout.NORTH,
							inventoryPanel, 0, SpringLayout.NORTH, contentPane);
					sl_contentPane
							.putConstraint(SpringLayout.EAST, inventoryPanel,
									-10, SpringLayout.EAST, contentPane);
					contentPane.add(inventoryPanel);
				}
				if (statusPanel != null) {
					contentPane.remove(statusPanel);
					statusPanel = new StatusPanel();
					sl_contentPane.putConstraint(SpringLayout.NORTH,
							statusPanel, 0, SpringLayout.SOUTH, inventoryPanel);
					sl_contentPane.putConstraint(SpringLayout.EAST,
							statusPanel, -10, SpringLayout.EAST, contentPane);
					contentPane.add(statusPanel);
				}
			}
			SwingUtilities.updateComponentTreeUI(MainFrame.this);
			timer.restart();
		}

	}

}
