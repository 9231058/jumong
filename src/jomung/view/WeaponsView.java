package jomung.view;

import java.util.List;

import javax.swing.JComponent;

import jomung.Item;
import jomung.Weapon;

public class WeaponsView {
	private WeaponsView() {

	}

	public static JComponent[] view(List<Item> items) {
		JComponent[] components;
		int size = 0;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Weapon) {
				size++;
			}
		}
		components = new JComponent[size];
		int index = 0;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Weapon) {
				components[index] = new ItemComponent(items.get(i));
				index++;
			}
		}
		return components;
	}
}
