package gomoku;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameplayControl implements ActionListener
{
	// Private data fields for the container and chat client.
	private JPanel				container;
	private ChatClient			client;
	private GameBoardControl	gbc;
	private GameBoard			gb;
	private boolean				resign	= false;

	// Constructor for the login controller.
	public GameplayControl(JPanel container, ChatClient client)
	{
		this.container = container;
		this.client = client;
		gbc = new GameBoardControl(client);
		gb = new GameBoard(gbc);
	}

	public GameBoard getGameBoard()
	{
		return gb;
	}

	public void drawChess(Stone[][] board)
	{
		gb.updateBoard(board);
	}

	public void stopMouse()
	{
		gb.stopMouse(gbc);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		if (command == "Resign")
		{
			try
			{
				client.sendToServer(new Move(-1, -1));
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resign = true;
		}
		else if (command == "Exit")
		{
			System.exit(0);
		}
	}

	public void displayLabel(String message)
	{
		// TODO Auto-generated method stub
		GameplayPanel gameplayPanel = (GameplayPanel) container.getComponent(3);
		gameplayPanel.setMessage(message);
	}

	public void setPlayer(boolean color)
	{
		GameplayPanel gp = (GameplayPanel) container.getComponent(3);
		gp.setPlayer(color);
	}
}