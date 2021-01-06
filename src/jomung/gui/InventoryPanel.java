package jomung.gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import jomung.enums.ItemType;
import jomung.view.ItemsView;

public class InventoryPanel extends JPanel {

	private static final long serialVersionUID = 8369700876554274301L;
	private SpringLayout sl_contentPane;
	private JComponent[] items;

	public InventoryPanel() {
		setPreferredSize(new Dimension(500, 500));
		sl_contentPane = new SpringLayout();
		setLayout(sl_contentPane);

		int row = 0;

		items = ItemsView.view(MainFrame.getInstance().getPlayer()
				.getItemsList());
		int index = 0;
		for (; row < items.length / 8 + 1; row++) {
			for (int i = 1; i <= 8 && index < items.length; index++, i++) {
				sl_contentPane.putConstraint(SpringLayout.NORTH, items[index],
						row * 50, SpringLayout.NORTH, this);
				sl_contentPane.putConstraint(SpringLayout.WEST, items[index],
						50 * i, SpringLayout.WEST, this);
				items[index].addMouseListener(new ItemUser(items[index]));
				add(items[index]);
			}
		}
	}

	private class ItemUser implements MouseListener {

		String itemName;

		public ItemUser(JComponent component) {
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
			if (itemName.equalsIgnoreCase(ItemType.shovel.getItemName())) {
				int x = Integer.parseInt(JOptionPane.showInputDialog(
						MainFrame.getInstance(), "Please Enter X"));
				int y = Integer.parseInt(JOptionPane.showInputDialog(
						MainFrame.getInstance(), "Please Enter Y"));
				MainFrame.getInstance().shovel(x, y);
				return;
			}
			MainFrame.getInstance().useItem(itemName);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

	}

}
