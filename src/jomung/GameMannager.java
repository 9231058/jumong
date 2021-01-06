package jomung;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import jomung.database.DataBase;
import jomung.gameplay.GamePlayClient;
import jomung.gameplay.GamePlayServer;
import jomung.gui.MainFrame;
import jomung.map.MapFactory;
import jomung.map.XMLMapFactory;
import jomung.res.R;
import jomung.sound.SoundPlayer;
import jomung.system.SysR;
import jomung.ui.PlayerUI2D;
import jomung.ui.PlayerUIServer;
import jomung.ui.UI;

public class GameMannager {
	private static GameMannager instance;
	private MapFactory mapFactory;
	private ArrayList<UI> pieces;

	public static GameMannager getInstance() {
		if (instance == null) {
			instance = new GameMannager();
		}
		return instance;
	}

	private GameMannager() {
		mapFactory = new XMLMapFactory();
	}

	public void setMapFactory(MapFactory mapFactory) {
		this.mapFactory = mapFactory;
	}

	public ArrayList<UI> getPieces() {
		return pieces;
	}

	public void start() {
		mapFactory.create();
		mapFactory.setR();
		R.getInstance().init();
		pieces = new ArrayList<>();
		for (int i = 0; i < R.getInstance().getPlayers().length; i++) {
			pieces.add(new PlayerUI2D(R.getInstance().getPlayers()[i]));
		}
		GamePlayServer.getInstance().start();
		MainFrame.getInstance().setVisible(true);
		SoundPlayer.getInstance().start();
	}

	public void load(String path) {
		SysR.setPath(path);
		SysR.getInstance().start();
		DataBase.setInstance((DataBase) SysR.getInstance().getObject(
				DataBase.class));
		EnemyMannager.setInstance((EnemyMannager) SysR.getInstance().getObject(
				EnemyMannager.class));
		DataBase.getInstance().start();
		pieces = new ArrayList<>();
		for (int i = 0; i < DataBase.getInstance().getPlayers().length; i++) {
			pieces.add(new PlayerUI2D(DataBase.getInstance().getPlayers()[i]));
		}
		GamePlayServer.getInstance().start();
		MainFrame.getInstance().setVisible(true);
	}

	public void save(String path) {
		SysR.setPath(path);
		SysR.getInstance().addObject(DataBase.getInstance(), DataBase.class);
		SysR.getInstance().addObject(EnemyMannager.getInstance(),
				EnemyMannager.class);
		SysR.getInstance().finish();
	}

	public void server() {
		mapFactory.setPath("XMLMapMultiplayer.xml");
		mapFactory.create();
		mapFactory.setR();
		R.getInstance().init();
		pieces = new ArrayList<>();
		pieces.add(new PlayerUI2D(R.getInstance().getPlayers()[0]));
		try {
			ServerSocket serverSocket = new ServerSocket(1373);
			for (int i = 1; i < R.getInstance().getPlayers().length; i++) {
				pieces.add(new PlayerUIServer(R.getInstance().getPlayers()[i],
						serverSocket.accept()));
			}
			serverSocket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		GamePlayServer.getInstance().start();
		MainFrame.getInstance().setVisible(true);
	}

	public void client(String IP) {
		GamePlayClient.getInstance().setIP(IP);
		GamePlayClient.getInstance().start();
		MainFrame.getInstance().setVisible(true);
	}
}
