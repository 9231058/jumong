package jomung.ui;

import jomung.enums.Command;

public interface UI {
	void nextTurn();

	void update(Command command, Object... objects);
}
