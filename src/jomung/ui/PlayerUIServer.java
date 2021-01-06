package jomung.ui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jomung.enums.Command;
import jomung.gameplay.GamePlayServer;
import jomung.movingobject.Player;

public class PlayerUIServer implements UI {

	private Socket socket;
	private ObjectOutputStream ostream;
	private ObjectInputStream istream;

	public PlayerUIServer(Player player, Socket socket) {
		this.socket = socket;
		try {
			ostream = new ObjectOutputStream(socket.getOutputStream());
			ostream.flush();
			istream = new ObjectInputStream(socket.getInputStream());
			ostream.writeObject(player);
			ostream.flush();
		} catch (IOException exception) {

		}
	}

	@Override
	public void nextTurn() {
		try {
			ostream.writeUTF("NEXTTURN");
			ostream.flush();
			String respond = istream.readUTF();
			if (respond.equalsIgnoreCase("OK")) {
				System.err.println("Turn complete");
				return;
			} else if (respond.equalsIgnoreCase("GAMEOVER")) {
				socket.close();
				GamePlayServer.getInstance().gameOver(this);
			} else if (respond.equalsIgnoreCase("WIIN")) {
				socket.close();
				GamePlayServer.getInstance().win(this);
			}
		} catch (IOException exception) {

		}
	}

	@Override
	public void update(Command command, Object... objects) {
	}

}
