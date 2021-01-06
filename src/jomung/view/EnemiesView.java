package jomung.view;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import jomung.movingobject.Enemy;

public class EnemiesView {
	private EnemiesView() {

	}

	public static JComponent[] view(List<Enemy> enemies) {
		JComponent[] components = new JComponent[enemies.size()];
		for (int i = 0; i < enemies.size(); i++) {
			components[i] = new EnemyComponent(new ImageIcon("icons/Enemy.png"));
		}
		return components;
	}
}
