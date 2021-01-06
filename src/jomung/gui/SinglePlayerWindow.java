package jomung.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import jomung.GameMannager;

public class SinglePlayerWindow extends JFrame {

	private static final long serialVersionUID = 5900184735118775754L;
	private JButton startButton;
	private JButton exitButton;
	private JButton loadButton;
	private SpringLayout sl_contentPane;
	private JPanel contentPane;

	public SinglePlayerWindow() {
		sl_contentPane = new SpringLayout();
		contentPane = new JPanel();
		contentPane.setLayout(sl_contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(300, 300));

		startButton = new JButton("Start");
		startButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, startButton, 50,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, startButton, 50,
				SpringLayout.WEST, contentPane);
		startButton.addActionListener(new Start());
		contentPane.add(startButton);

		loadButton = new JButton("Load");
		loadButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, loadButton, 10,
				SpringLayout.SOUTH, startButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, loadButton, 50,
				SpringLayout.WEST, contentPane);
		loadButton.addActionListener(new Load());
		contentPane.add(loadButton);

		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(200, 50));
		sl_contentPane.putConstraint(SpringLayout.NORTH, exitButton, 10,
				SpringLayout.SOUTH, loadButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, exitButton, 50,
				SpringLayout.WEST, contentPane);
		exitButton.addActionListener(new Exit());
		contentPane.add(exitButton);

		setContentPane(contentPane);
	}

	private class Start implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			SinglePlayerWindow.this.dispose();
			GameMannager.getInstance().start();
		}

	}

	private class Load implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Load J O M U N G");
			if (chooser.showOpenDialog(SinglePlayerWindow.this) == JFileChooser.APPROVE_OPTION) {
				GameMannager.getInstance().load(
						chooser.getSelectedFile().getPath());
				SinglePlayerWindow.this.dispose();
			}
		}
	}

	private class Exit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}

	}

}
