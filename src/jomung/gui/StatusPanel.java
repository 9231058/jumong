package jomung.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 6369802841749292335L;
	private JProgressBar health;
	private JProgressBar energy;
	private JLabel score;
	private SpringLayout sl_contentPane;

	public StatusPanel() {
		setPreferredSize(new Dimension(200, 100));
		sl_contentPane = new SpringLayout();
		setLayout(sl_contentPane);

		health = new JProgressBar(0, 100);
		health.setString("Health");
		health.setValue(MainFrame.getInstance().getPlayer().getHealth());
		health.setStringPainted(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, health, 0,
				SpringLayout.NORTH, this);
		sl_contentPane.putConstraint(SpringLayout.WEST, health, 0,
				SpringLayout.WEST, this);
		add(health);

		energy = new JProgressBar(0, 100);
		energy.setString("Energy");
		energy.setValue(MainFrame.getInstance().getPlayer().getEnergy());
		energy.setStringPainted(true);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, energy, 0,
				SpringLayout.SOUTH, this);
		sl_contentPane.putConstraint(SpringLayout.WEST, energy, 0,
				SpringLayout.WEST, this);
		add(energy);

		score = new JLabel();
		score.setText("Score : "
				+ MainFrame.getInstance().getPlayer().getScore());
		sl_contentPane.putConstraint(SpringLayout.NORTH, score, 10,
				SpringLayout.SOUTH, health);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, score, -10,
				SpringLayout.NORTH, energy);
		sl_contentPane.putConstraint(SpringLayout.EAST, score, 0,
				SpringLayout.EAST, health);
		sl_contentPane.putConstraint(SpringLayout.WEST, score, 0,
				SpringLayout.WEST, health);
		add(score);

	}

}
