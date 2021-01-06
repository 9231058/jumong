package jomung.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import jomung.GameMannager;

public class MultiPlayerWindow extends JFrame {

	private static final long serialVersionUID = 6856746728118683736L;
	private JButton listenButton;
	private JButton joinButton;
	private SpringLayout sl_contentPane;
	private JPanel contentPane;

	public MultiPlayerWindow() {
		sl_contentPane = new SpringLayout();
		contentPane = new JPanel();
		contentPane.setLayout(sl_contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(300, 300));

		listenButton = new JButton("Listen");
		listenButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, listenButton, 50,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, listenButton, 50,
				SpringLayout.WEST, contentPane);
		listenButton.addActionListener(new Listen());
		contentPane.add(listenButton);

		joinButton = new JButton("Join");
		joinButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, joinButton, 10,
				SpringLayout.SOUTH, listenButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, joinButton, 50,
				SpringLayout.WEST, contentPane);
		joinButton.addActionListener(new Join());
		contentPane.add(joinButton);

		setContentPane(contentPane);
	}

	private class Listen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			GameMannager.getInstance().server();
			MultiPlayerWindow.this.dispose();
		}

	}

	private class Join implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String IP = JOptionPane.showInputDialog("Server IP Address : ");
			GameMannager.getInstance().client(IP);
			MultiPlayerWindow.this.dispose();
		}

	}
}
