package jomung.gameplay;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jomung.movingobject.Player;
import jomung.ui.PlayerUI2D;
import jomung.ui.UI;

public class GamePlayClient extends Thread {
	private static GamePlayClient instance;
	private PlayerUI2D piece;
	private ObjectOutputStream ostream;
	private ObjectInputStream istream;
	private volatile boolean gameOver = false;
	private volatile boolean win = false;
	Socket socket;
	private String IP;

	public static GamePlayClient getInstance() {
		if (instance == null) {
			instance = new GamePlayClient();
		}
		return instance;
	}

	public GamePlayClient() {
	}

	@Override
	public void run() {
		try {
			socket = new Socket(IP, 1373);
			ostream = new ObjectOutputStream(socket.getOutputStream());
			ostream.flush();
			istream = new ObjectInputStream(socket.getInputStream());
			Player player = (Player) istream.readObject();
			player.setIP(IP);
			piece = new PlayerUI2D(player, true);
			while (!gameOver && !win) {
				String request = istream.readUTF();
				if (request.equalsIgnoreCase("NEXTTURN")) {
					System.err.println("Next Turn ...");
					piece.nextTurn();
					ostream.writeUTF("OK");
					ostream.flush();
				}
			}
		} catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public void gameOver(UI piece) {
		gameOver = true;
		try {
			ostream.writeUTF("GAMEOVER");
			ostream.flush();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void win(UI piece) {
		win = true;
		try {
			ostream.writeUTF("WIN");
			ostream.flush();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void setIP(String IP) {
		this.IP = IP;
	}
}
