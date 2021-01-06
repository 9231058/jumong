package jomung.gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.SpringLayout;

import jomung.Room;
import jomung.enums.ItemType;
import jomung.view.ItemsView;

public class RoomDialog extends JDialog {

	private static final long serialVersionUID = 4564709351322687418L;
	private JComponent[] items;
	private SpringLayout sl_contentPane;

	public RoomDialog(Room room) {
		super(MainFrame.getInstance(), "Room", true);
		setSize(new Dimension(500, 500));
		setLocationRelativeTo(MainFrame.getInstance());
		sl_contentPane = new SpringLayout();
		setLayout(sl_contentPane);

		int row = 0;
		boolean found = false;
		for (int i = 0; i < room.getPlayersList().size(); i++) {
			if (room.getPlayersList().get(i)
					.equals(MainFrame.getInstance().getPlayer())) {
				found = true;
			}
		}

		items = ItemsView.view(room.getItemsList());
		int index = 0;
		for (; row < items.length / 8 + 1; row++) {
			for (int i = 1; i <= 8 && index < items.length; index++, i++) {
				sl_contentPane.putConstraint(SpringLayout.NORTH, items[index],
						row * 50, SpringLayout.NORTH, this);
				sl_contentPane.putConstraint(SpringLayout.WEST, items[index],
						50 * i, SpringLayout.WEST, this);
				if (found) {
					items[index].addMouseListener(new ItemGetter(items[index]));
				}
				add(items[index]);
			}
		}
	}

	private class ItemGetter implements MouseListener {

		private JComponent component;
		private String itemName;

		public ItemGetter(JComponent component) {
			this.component = component;
			itemName = component.toString();
		}

		@Override
		public void mouseClicked(MouseEvent event) {
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if (itemName.equalsIgnoreCase(ItemType.Chest.getItemName())) {
				return;
			}
			MainFrame.getInstance().getItem(itemName);
			component.setVisible(false);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

	}

}
