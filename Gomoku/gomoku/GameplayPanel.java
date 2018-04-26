package gomoku;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameplayPanel extends JPanel
{
	private JLabel	label;
	private JLabel	labelTitle;

	public GameplayPanel(GameplayControl gpc)
	{
		JPanel right = new JPanel(new GridLayout(1, 1, 480, 480));
		gpc.getGameBoard().setVisible(true);
		right.add(gpc.getGameBoard());
		JPanel left = new JPanel(new GridLayout(3, 1, 100, 160));
		labelTitle = new JLabel("Welcome to Gomoku!");
		labelTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 32));
		JPanel but = new JPanel(new GridLayout(1, 2, 100, 160));

		JButton exit = new JButton("Exit");
		JButton quit = new JButton("Resign");
		quit.addActionListener(gpc);
		exit.addActionListener(gpc);
		but.add(quit);
		but.add(exit);
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 44));
		left.add(labelTitle);
		left.add(label);
		left.add(but);

		JPanel jp = new JPanel(new GridLayout(1, 2, 1, 10));
		jp.add(left);
		jp.add(right);
		this.add(jp);
		this.setVisible(true);
	}

	public void setMessage(String message)
	{
		label.setText(message);
	}

	public void setPlayer(boolean color)
	{
		String result;
		if (color)
		{
			result = "white";
		} else
		{
			result = "black";
		}
		labelTitle.setText(labelTitle.getText() + " - " + result);
	}
}
