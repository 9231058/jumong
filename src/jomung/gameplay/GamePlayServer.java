package jomung.gameplay;

import java.util.List;

import jomung.EnemyMannager;
import jomung.GameMannager;
import jomung.ui.UI;

public class GamePlayServer extends Thread {
	private static GamePlayServer instance;
	private List<UI> pieces;
	private volatile boolean win = false;

	public static GamePlayServer getInstance() {
		if (instance == null) {
			instance = new GamePlayServer();
		}
		return instance;
	}

	private GamePlayServer() {
		pieces = GameMannager.getInstance().getPieces();
	}

	@Override
	public void run() {
		while (pieces.size() != 0 && !win) {
			for (int i = 0; i < pieces.size(); i++) {
				pieces.get(i).nextTurn();
			}
			EnemyMannager.getInstance().run();
		}
	}

	public void gameOver(UI piece) {
		pieces.remove(piece);
	}

	public void win(UI piece) {
		win = true;
	}

}