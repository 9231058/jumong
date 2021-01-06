package jomung.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -621777937389967567L;
	private JButton singlePlayerButton;
	private JButton multiPlayerButton;
	private JButton exitButton;
	private SpringLayout sl_contentPane;
	private JPanel contentPane;

	public MainWindow() {
		contentPane = new JPanel();
		sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		setSize(new Dimension(300, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		singlePlayerButton = new JButton("Single Player");
		singlePlayerButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, singlePlayerButton,
				50, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, singlePlayerButton, 50,
				SpringLayout.WEST, contentPane);
		singlePlayerButton.addActionListener(new SinglePlayerStarter());
		contentPane.add(singlePlayerButton);

		multiPlayerButton = new JButton("Multi Player");
		multiPlayerButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, multiPlayerButton, 10,
				SpringLayout.SOUTH, singlePlayerButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, multiPlayerButton, 50,
				SpringLayout.WEST, contentPane);
		multiPlayerButton.addActionListener(new MultiPlayerStarter());
		contentPane.add(multiPlayerButton);

		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, exitButton, 10,
				SpringLayout.SOUTH, multiPlayerButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, exitButton, 50,
				SpringLayout.WEST, contentPane);
		exitButton.addActionListener(new Exit());
		contentPane.add(exitButton);

		setContentPane(contentPane);
	}

	private class SinglePlayerStarter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			new SinglePlayerWindow().setVisible(true);
			MainWindow.this.dispose();
		}

	}

	private class MultiPlayerStarter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			new MultiPlayerWindow().setVisible(true);
			MainWindow.this.dispose();
		}

	}

	private class Exit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}

	}

}
