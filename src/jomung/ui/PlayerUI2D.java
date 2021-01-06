package jomung.ui;

import javax.swing.JOptionPane;

import jomung.enums.Command;
import jomung.enums.Direction;
import jomung.gameplay.GamePlayClient;
import jomung.gameplay.GamePlayServer;
import jomung.gui.BattleDialog;
import jomung.gui.MainFrame;
import jomung.movingobject.Player;

public class PlayerUI2D implements UI {

	private volatile boolean haveNewCommand = false;
	private Player player;
	private Command command;
	private Object[] objects;
	private boolean client = false;

	public PlayerUI2D(Player player) {
		this.player = player;
		MainFrame.getInstance().setUI(this);
	}

	public PlayerUI2D(Player player, boolean client) {
		this(player);
		this.client = client;
	}

	@Override
	public void nextTurn() {
		player.setCurrentLocationSeeAble();
		player.ready();
		while (!haveNewCommand) {
			;
		}
		haveNewCommand = false;
		boolean move = false;
		try {
			switch (command) {
			case MOVE_LEFT:
				player.move(Direction.LEFT);
				move = true;
				break;
			case MOVE_DOWN:
				player.move(Direction.DOWN);
				move = true;
				break;
			case MOVE_RIGHT:
				player.move(Direction.RIGHT);
				move = true;
				break;
			case MOVE_UP:
				player.move(Direction.UP);
				move = true;
				break;
			case GET_ITEM:
				player.getItem((String) objects[0]);
				player.setCurrentLocationSeeAble();
				break;
			case USE_ITEM:
				player.useItem((String) objects[0]);
				player.setCurrentLocationSeeAble();
				break;
			case DROP_ITEM:
				player.dropItem((String) objects[0]);
				player.setCurrentLocationSeeAble();
				break;
			case SHOVEL:
				player.shovel((int) objects[0], (int) objects[1]);
				player.setCurrentLocationSeeAble();
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}
		if (player.haveEnemies() && move) {
			new BattleDialog().setVisible(true);
			war();
		}
		if (!player.isAlive()) {
			if (!client) {
				GamePlayServer.getInstance().gameOver(this);
			} else {
				GamePlayClient.getInstance().gameOver(this);
			}
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		if (player.isWinner()) {
			if (!client) {
				GamePlayServer.getInstance().win(this);
			} else {
				GamePlayClient.getInstance().win(this);
			}
			JOptionPane.showMessageDialog(null, "WINNER");
		}
	}

	public void war() {
		boolean war = true;
		while (war) {
			while (!haveNewCommand) {
				;
			}
			haveNewCommand = false;
			switch (command) {
			case KILL_ENEMY:
				player.useWeapon((String) objects[0], (int) objects[1]);
				break;
			case END_WAR:
				war = false;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void update(Command command, Object... objects) {
		haveNewCommand = true;
		this.command = command;
		this.objects = objects;
	}

	public void updateWar(Command command, Object... objects) {
		haveNewCommand = true;
		this.command = command;
		this.objects = objects;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isClient() {
		return client;
	}

}
