package jomung.view;

import java.util.List;

import javax.swing.JComponent;

import jomung.Item;

public class ItemsView {

	private ItemsView() {
	}

	public static JComponent[] view(List<Item> items) {
		JComponent[] components = new JComponent[items.size()];
		for (int i = 0; i < items.size(); i++) {
			components[i] = new ItemComponent(items.get(i));
		}
		return components;
	}
}
