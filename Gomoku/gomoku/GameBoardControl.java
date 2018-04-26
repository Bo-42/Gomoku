package gomoku;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GameBoardControl implements MouseListener
{
	private ChatClient client;

	public GameBoardControl(ChatClient client)
	{
		this.client = client;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		double xofpanel = arg0.getX();
		double yofpanel = arg0.getY();

		int movex = (int) Math.round((xofpanel - 10) / 30);
		int movey = (int) Math.round((yofpanel - 10) / 30);
		Move mo = new Move(movex, movey);
		try
		{
			client.sendToServer(mo);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
