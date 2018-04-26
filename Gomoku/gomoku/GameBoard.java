package gomoku;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class GameBoard extends JPanel
{
	private Stone[][] board;

	public GameBoard(GameBoardControl gbc)
	{
		this.addMouseListener(gbc);
		board = new Stone[15][15];
		repaint();
	}

	public void stopMouse(GameBoardControl gbc)
	{
		this.removeMouseListener(gbc);
	}

	public void updateBoard(Stone[][] board)
	{
		this.board = new Stone[15][15];
		for (int r = 0; r < 15; r++)
		{
			for (int c = 0; c < 15; c++)
			{
				if (board[r][c] == null)
				{
					continue;
				}
				this.board[r][c] = new Stone(board[r][c].getColor());
			}
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		for (int r = 0; r < 15; r++)
		{
			g2d.setColor(Color.black);
			g2d.drawLine(10, r * 30 + 10, 430, r * 30 + 10);
			g2d.drawLine(r * 30 + 10, 10, r * 30 + 10, 430);
			for (int c = 0; c < 15; c++)
			{
				if (board[r][c] == null)
				{
					continue;
				} else if (board[r][c].getColor() == true)
				{
					g2d.setColor(Color.white);
					g2d.fill(new Ellipse2D.Double(r * 30 + 5, c * 30 + 5, 15, 15));
				} else if (board[r][c].getColor() == false)
				{
					g2d.setColor(Color.black);
					g2d.fill(new Ellipse2D.Double(r * 30 + 5, c * 30 + 5, 15, 15));
				}
			}
		}
	}
}
