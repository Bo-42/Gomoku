package gomoku;

import java.io.Serializable;
import java.util.Arrays;

public class GameData implements Serializable 
{
	// data members
	private boolean whoseTurn;	// false = black, true = white
	private Stone[][] board;	// the 15x15 grid (2D array) that stores stone objects
	
	// default constructor
	public GameData() 
	{
		super();
		board = new Stone[15][15];
		whoseTurn = false;
	}
	
	// parameterized constructor
	public GameData( Stone[][] board, boolean whoseTurn) 
	{
		super();
		this.whoseTurn = whoseTurn;
		this.board = board;
	}

	public Stone[][] getBoard() 
	{
		return board;
	}

	public void setBoard( Stone[][] board ) 
	{
		this.board = board;
	}
	
	public boolean isWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(boolean whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	public boolean checkMove( Move move )
	{	// returns 1 (true) if game board grid location from move (row, column) contains a stone
		// returns 0 (false) if game board grid location from move (row, column) is empty
		
		if ( board[move.getRow()][move.getColumn()] == null)
			return false;
		else
			return true;
	}
	
	public void addStone( Move move, Stone stone )
	{
		this.board[move.getRow()][move.getColumn()] = new Stone(stone.getColor());
	}
	
	public boolean checkWin( Move move)
	{	// returns a 1 (true) if a move creates a winning condition
		// returns a 0 (false) if a move does not create a winning condition
		if (checkHorizontal(move) || checkVertical(move) || checkDiagonal(move))
		{
			return true;
		}
		return false;
	}
	
	private boolean checkHorizontal(Move move)
	{
		Stone moveColor = board[move.getRow()][move.getColumn()];
		int i = move.getColumn();
		int idx = 1;
		int lineCount = 1;
		boolean left, right;
		left = right = true;
		
		for (; idx < 5; idx++)
		{
			if (!left || (i - idx) < 0 || board[move.getRow()][i - idx] == null)
			{
				left = false;
			}
			else
			{
				if (moveColor.equals(board[move.getRow()][i - idx]))
				{
					lineCount++;
				}
				else
				{
					left = false;
				}
			}
			if (!right || (i + idx) > 14 || board[move.getRow()][i + idx] == null)
			{
				right = false;
			}
			else
			{
				if (moveColor.equals(board[move.getRow()][i + idx]))
				{
					lineCount++;
				}
				else
				{
					right = false;
				}
			}
			if (lineCount > 4)
			{
				return true;
			}
			else if (!left && !right)
			{
				return false;
			}
		}
		return false;
	}
	
	private boolean checkVertical(Move move)
	{
		Stone moveColor = board[move.getRow()][move.getColumn()];
		int i = move.getRow();
		int idx = 1;
		int lineCount = 1;
		boolean up, down;
		up = down = true;
		
		for (; idx < 5; idx++)
		{
			if (!up || (i - idx) < 0 || board[i - idx][move.getColumn()] == null)
			{
				up = false;
			}
			else
			{
				if (moveColor.equals(board[i - idx][move.getColumn()]))
				{
					lineCount++;
				}
				else
				{
					up = false;
				}
			}
			if (!down || (i + idx) > 14 || board[i + idx][move.getColumn()] == null)
			{
				down = false;
			}
			else
			{
				if (moveColor.equals(board[i + idx][move.getColumn()]))
				{
					lineCount++;
				}
				else
				{
					down = false;
				}
			}
			if (lineCount > 4)
			{
				return true;
			}
			else if (!up && !down)
			{
				return false;
			}
		}
		return false;
	}
	
	private boolean checkDiagonal(Move move)
	{
		Stone moveColor = board[move.getRow()][move.getColumn()];
		int r = move.getRow();
		int c = move.getColumn();
		int idx = 1;
		int lineCount = 1;
		boolean topL, topR, bottomL, bottomR;
		topL = topR = bottomL = bottomR = true;
		
		for (; idx < 5; idx++)
		{
			if (!topL || (r - idx) < 0 || (c - idx) < 0 || board[r - idx][c - idx] == null)
			{
				topL = false;
			}
			else
			{
				if (moveColor.equals(board[r - idx][c - idx]))
				{
					lineCount++;
				}
				else
				{
					topL = false;
				}
			}
			if (!bottomR || (r + idx) > 14 || (c + idx) > 14 || board[r + idx][c + idx] == null)
			{
				bottomR = false;
			}
			else
			{
				if (moveColor.equals(board[r + idx][c + idx]))
				{
					lineCount++;
				}
				else
				{
					bottomR = false;
				}
			}
			if (lineCount > 4)
			{
				return true;
			}
			else if (!topL && !bottomR)
			{
				break;
			}
		}
		
		lineCount = 1;
		idx = 1;
		
		for (; idx < 5; idx++)
		{
			if (!topR || (r - idx) < 0 || (c + idx) > 14 || board[r - idx][c + idx] == null)
			{
				topR = false;
			}
			else
			{
				if (moveColor.equals(board[r - idx][c + idx]))
				{
					lineCount++;
				}
				else
				{
					topR = false;
				}
			}
			if (!bottomL || (r + idx) > 14 || (c - idx) < 0 || board[r + idx][c - idx] == null)
			{
				bottomL = false;
			}
			else
			{
				if (moveColor.equals(board[r + idx][c - idx]))
				{
					lineCount++;
				}
				else
				{
					bottomL = false;
				}
			}
			if (lineCount > 4)
			{
				return true;
			}
			else if (!topR && !bottomL)
			{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString() 
	{
		return "GameData [board=" + Arrays.toString(board) + "]";
	}
	
}	// end class
